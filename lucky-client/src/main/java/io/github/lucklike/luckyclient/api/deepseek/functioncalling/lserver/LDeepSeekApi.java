package io.github.lucklike.luckyclient.api.deepseek.functioncalling.lserver;

import com.luckyframework.common.ConfigurationMap;
import com.luckyframework.common.ContainerUtils;
import com.luckyframework.common.StringUtils;
import com.luckyframework.httpclient.core.meta.BodyObject;
import com.luckyframework.httpclient.core.meta.Request;
import com.luckyframework.httpclient.core.meta.Response;
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
import io.github.lucklike.luckyclient.api.deepseek.functioncalling.DeepSeekApiFunctionCallingApi;
import io.github.lucklike.luckyclient.api.deepseek.functioncalling.reequest.CompletionRequest;
import io.github.lucklike.luckyclient.api.deepseek.functioncalling.reequest.MessageUtils;

import java.util.ArrayList;
import java.util.List;

@PrintLogProhibition
@Timeout(readTimeout = 1000000)
@HttpClient("${DeepSeek.url}")
@StaticHeader("Authorization: Bearer ${DeepSeek.apiKey}")
public interface LDeepSeekApi {

    ThreadLocal<List<ConfigurationMap>> MESSAGES = new ThreadLocal<>();

    @RespConvert
    @Post("/chat/completions")
    String completions(String content);


    //-----------------------------------------------------------------------------
    //                          Convert Method
    //-----------------------------------------------------------------------------


    static String completions$Convert(MethodContext mc) {
        String content = mc.getRootVar("$body$.choices[0].message.content", String.class);
        if (StringUtils.hasText(content)) {
            return content;
        }
        return mc.getRootVar("$finalResult", String.class);
    }


    //-----------------------------------------------------------------------------
    //                          Callback Method
    //-----------------------------------------------------------------------------

    @Callback(lifecycle = Lifecycle.REQUEST)
    static void reqCallback(@Param("#{p0}") String content,
                            Request request,
                            FunctionCallUtils functionCallUtils) {


        CompletionRequest reqBody = new CompletionRequest();
        reqBody.setModel("deepseek-chat");
        if (StringUtils.hasText(content)) {
            reqBody.appendMessage(MessageUtils.createUserMessage(content));
        }

        if (MESSAGES.get() == null) {
            if (ContainerUtils.isNotEmptyCollection(functionCallUtils.getToolCallConfigs())) {
                reqBody.setTools(functionCallUtils.getToolCallConfigs());
            }
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
                             FunctionCallUtils functionCallUtils,
                             LDeepSeekApi callingApi) throws Exception {
        ConfigurationMap result = response.getConfigMapResult();
        ConfigurationMap message = result.getMap("choices[0].message");

        if (message.containsConfigKey("tool_calls")) {
            // 构造新的message
            List<ConfigurationMap> messages = new ArrayList<>();
            messages.add(message);

            ConfigurationMap toolResultMessage = new ConfigurationMap();
            List<ConfigurationMap> toolCalls = message.getMapList("tool_calls");
            for (ConfigurationMap toolCall : toolCalls) {
                toolResultMessage.addProperty("role", "tool");
                toolResultMessage.addProperty("tool_call_id", toolCall.getString("id"));
                toolResultMessage.addProperty("content", functionCallUtils.callTool(toolCall.getString("function.name"), toolCall.getString("function.arguments")));
                messages.add(toolResultMessage);
            }
            MESSAGES.set(messages);

            final String finalResult = callingApi.completions(content);
            mc.getContextVar().addRootVariable("$finalResult", finalResult);
        }

    }
}
