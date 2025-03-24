package io.github.lucklike.luckyclient.api.deepseek.functioncalling;

import com.luckyframework.common.ConfigurationMap;
import com.luckyframework.common.StringUtils;
import com.luckyframework.httpclient.core.meta.BodyObject;
import com.luckyframework.httpclient.core.meta.Request;
import com.luckyframework.httpclient.core.meta.Response;
import com.luckyframework.httpclient.proxy.CommonFunctions;
import com.luckyframework.httpclient.proxy.annotations.Post;
import com.luckyframework.httpclient.proxy.annotations.PrintLogProhibition;
import com.luckyframework.httpclient.proxy.annotations.RespConvert;
import com.luckyframework.httpclient.proxy.annotations.StaticHeader;
import com.luckyframework.httpclient.proxy.annotations.Timeout;
import com.luckyframework.httpclient.proxy.context.MethodContext;
import com.luckyframework.httpclient.proxy.spel.hook.Lifecycle;
import com.luckyframework.httpclient.proxy.spel.hook.callback.Callback;
import com.luckyframework.reflect.Param;
import io.github.lucklike.httpclient.discovery.HttpClient;
import io.github.lucklike.luckyclient.api.deepseek.functioncalling.reequest.CompletionRequest;
import io.github.lucklike.luckyclient.api.deepseek.functioncalling.reequest.MessageUtils;
import io.github.lucklike.luckyclient.api.roll.CurrentLimitationOilPriceApi;

import java.util.ArrayList;
import java.util.List;

@Timeout(readTimeout = 1000000)
@HttpClient("${DeepSeek.url}")
@StaticHeader("Authorization: Bearer ${DeepSeek.apiKey}")
public interface DeepSeekApiFunctionCallingApi {

    ThreadLocal<List<ConfigurationMap>> MESSAGES = new ThreadLocal<>();


    @RespConvert
    @Post("/chat/completions")
    String completions(String content);

    static String  completions$Convert(MethodContext mc) {
        String content = mc.getRootVar("$body$.choices[0].message.content", String.class);
        if (StringUtils.hasText(content)) {
            return content;
        }
        return mc.getRootVar("$finalResult", String.class);
    }


    @Callback(lifecycle = Lifecycle.REQUEST)
    static void reqCallback(@Param("#{p0}") String content, Request request) {
        CompletionRequest reqBody = new CompletionRequest();
        reqBody.setModel("deepseek-chat");
        if (StringUtils.hasText(content)) {
            reqBody.appendMessage(MessageUtils.createUserMessage(content));
        }

        if (MESSAGES.get() == null) {
            ConfigurationMap tool = new ConfigurationMap();
            tool.addProperty("type", "function");
            tool.addProperty("function.name", "query_oil_price");
            tool.addProperty("function.description", "石油价格查询接口，用户应提供一个城市");
            tool.addProperty("function.parameters.type", "object");
            tool.addProperty("function.parameters.properties.location.type", "string");
            tool.addProperty("function.parameters.properties.location.description", "城市，例如：湖北");
            tool.addProperty("function.parameters.required[0]", "location");
            reqBody.appendTool(tool);
        } else {
            reqBody.setMessages(MESSAGES.get());
            MESSAGES.remove();
        }


        request.setBody(BodyObject.jsonBody(reqBody));
    }


    @Callback(lifecycle = Lifecycle.RESPONSE)
    static void respCallback(Response response,
                             MethodContext mc,
                             @Param("#{p0}") String content,
                             CurrentLimitationOilPriceApi oilPriceApi,
                             DeepSeekApiFunctionCallingApi callingApi) throws Exception {
        ConfigurationMap result = response.getConfigMapResult();
        ConfigurationMap message = result.getMap("choices[0].message");

        if (message.containsConfigKey("tool_calls")) {
            // 构造新的message
            List<ConfigurationMap> messages = new ArrayList<>();
            messages.add(message);

            ConfigurationMap tool = message.getMap("tool_calls[0]");
            ConfigurationMap arguments = CommonFunctions._json(tool.getString("function.arguments"), ConfigurationMap.class);
            String toolId = tool.getString("id");
            String location = arguments.getString("location");

            ConfigurationMap toolResultMessage = new ConfigurationMap();
            toolResultMessage.addProperty("role", "tool");
            toolResultMessage.addProperty("tool_call_id", toolId);
            toolResultMessage.addProperty("content", oilPriceApi.limitQuery(location).toString());
            messages.add(toolResultMessage);

            MESSAGES.set(messages);

            final String finalResult = callingApi.completions(content);
            mc.getContextVar().addRootVariable("$finalResult", finalResult);
        }

    }

}
