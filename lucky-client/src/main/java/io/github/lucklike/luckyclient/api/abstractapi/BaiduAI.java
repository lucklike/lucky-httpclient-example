package io.github.lucklike.luckyclient.api.abstractapi;

import com.luckyframework.common.ConfigurationMap;
import com.luckyframework.common.Console;
import com.luckyframework.httpclient.core.meta.Request;
import com.luckyframework.httpclient.generalapi.describe.Describe;
import com.luckyframework.httpclient.generalapi.describe.DescribeFunction;
import com.luckyframework.httpclient.generalapi.token.JsonFileTokenManager;
import com.luckyframework.httpclient.proxy.annotations.Post;
import com.luckyframework.httpclient.proxy.annotations.PrintLogProhibition;
import com.luckyframework.httpclient.proxy.annotations.PropertiesJson;
import com.luckyframework.httpclient.proxy.annotations.Retryable;
import com.luckyframework.httpclient.proxy.annotations.StaticQuery;
import com.luckyframework.httpclient.proxy.context.MethodContext;
import com.luckyframework.httpclient.proxy.context.MethodMetaContext;
import com.luckyframework.httpclient.proxy.spel.hook.Lifecycle;
import com.luckyframework.httpclient.proxy.spel.hook.callback.Callback;
import com.luckyframework.httpclient.proxy.sse.Event;
import com.luckyframework.httpclient.proxy.sse.EventListener;
import com.luckyframework.httpclient.proxy.sse.Message;
import com.luckyframework.httpclient.proxy.sse.Sse;
import io.github.lucklike.httpclient.annotation.HttpClient;
import io.github.lucklike.luckyclient.api.baiduai.Token;
import io.github.lucklike.luckyclient.api.util.DelayedOutput;

import java.io.File;
import java.util.Scanner;

/**
 * @author fukang
 * @version 1.0.0
 * @date 2024/9/4 20:48
 */
@Retryable
@PrintLogProhibition
@HttpClient(name = "BAI-DU-AI", value = "https://aip.baidubce.com")
public abstract class BaiduAI extends JsonFileTokenManager<Token> implements EventListener {

    //---------------------------------------------------------------------
    //                          Service Method
    //---------------------------------------------------------------------

    public void questionsAndAnswersTest() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            Console.printlnGreen("ME: ");
            String context = scanner.nextLine();
            Console.printlnMulberry("\nAI:");
            questionsAndAnswers(context);
        }
    }

    //---------------------------------------------------------------------
    //                         Callback Method
    //---------------------------------------------------------------------

    @Callback(lifecycle = Lifecycle.METHOD_META)
    static void paramAddCallback1(MethodMetaContext context) {
        System.out.println("METHOD_META---》" + context.getCurrentAnnotatedElement());
    }

    @Callback(lifecycle = Lifecycle.REQUEST)
    static void paramAddCallback2(MethodContext context) {
        System.out.println("paramAddCallback2---》" + context.getCurrentAnnotatedElement());
    }

    @Callback(lifecycle = Lifecycle.REQUEST)
    static void paramAddCallback(MethodContext context, Request request, BaiduAI baiduAI) {
        request.addHeader("Content-Type", "application/json");
        if (DescribeFunction.nonTokenApi(context)) {
            request.addQueryParameter("access_token", baiduAI.getAccessToken());
        }
    }

    @Callback(lifecycle = Lifecycle.REQUEST)
    static void paramAddCallback3(MethodContext context) {
        System.out.println("paramAddCallback3---》" + context.getCurrentAnnotatedElement());
    }


    //---------------------------------------------------------------------
    //                          Api Method
    //---------------------------------------------------------------------


    @StaticQuery({
        "grant_type=client_credentials",
        "client_id=${baidu.API.APIKey}",
        "client_secret=${baidu.API.SecretKey}"
    })
    @Describe(isTokenApi = true)
    @Post("/oauth/2.0/token")
    abstract Token token();

    @PropertiesJson({
        "messages[0].role=user",
        "messages[0].content=#{content}",
        "stream=#{true}"
    })
    @Sse(expression = "#{$this$}")
    @Post("/rpc/2.0/ai_custom/v1/wenxinworkshop/chat/ernie-speed-128k")
    abstract void questionsAndAnswers(String content);


    //---------------------------------------------------------------------
    //                          EventListener Method
    //---------------------------------------------------------------------

    @Override
    public void onMessage(Event<Message> event) {
        Message message = event.getMessage();
        ConfigurationMap confMap = message.jsonDataToMap();
        if (!confMap.getBoolean("is_end")) {
            String result = confMap.getString("result");
            DelayedOutput.output(result, 70, 20);
        } else {
            Console.printlnMulberry("\n");
            DelayedOutput.clearOutputLength();
        }
    }

    //---------------------------------------------------------------------
    //                         Token Method
    //---------------------------------------------------------------------

    public String getAccessToken() {
        return getToken().getAccess_token();
    }

    @Override
    protected File getJsonFile() {
        return new File(System.getProperty("user.dir"),"baidu_token.json");
    }

    @Override
    protected Token refreshToken(Token oldToken) {
        Token token = token();
        token.generateExpiresTime();
        return token;
    }

    @Override
    protected boolean isExpires(Token token) {
        return token.isExpires();
    }

}
