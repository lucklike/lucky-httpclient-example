package io.github.lucklike.luckyclient.api.abstractapi;

import com.luckyframework.common.ConfigurationMap;
import com.luckyframework.common.Console;
import com.luckyframework.httpclient.core.meta.Request;
import com.luckyframework.httpclient.generalapi.describe.DescribeFunction;
import com.luckyframework.httpclient.generalapi.describe.TokenApi;
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
import com.luckyframework.httpclient.proxy.sse.AnnotationEventListener;
import com.luckyframework.httpclient.proxy.sse.Event;
import com.luckyframework.httpclient.proxy.sse.EventListener;
import com.luckyframework.httpclient.proxy.sse.Message;
import com.luckyframework.httpclient.proxy.sse.OnMessage;
import com.luckyframework.httpclient.proxy.sse.Sse;
import com.luckyframework.reflect.Param;
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
@HttpClient(name = "annBaiduAI", value = "https://aip.baidubce.com")
public abstract class BaiduAI extends JsonFileTokenManager<Token> {

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

    @Callback(lifecycle = Lifecycle.REQUEST)
    static void paramAddCallback(MethodContext context, Request request, BaiduAI baiduAI) {
        if (DescribeFunction.needToken(context)) {
            request.addQueryParameter("access_token", baiduAI.getAccessToken());
        }
    }

    //---------------------------------------------------------------------
    //                          Api Method
    //---------------------------------------------------------------------


    @StaticQuery({
            "grant_type=client_credentials",
            "client_id=${baidu.API.APIKey}",
            "client_secret=${baidu.API.SecretKey}"
    })
    @TokenApi
    @Post("/oauth/2.0/token")
    abstract Token token();

    @PropertiesJson({
            "messages[0].role=user",
            "messages[0].content=#{content}",
            "stream=#{true}"
    })
    @Sse(listenerClass = BaiduSseEventListener.class)
    @Post("/rpc/2.0/ai_custom/v1/wenxinworkshop/chat/ernie-speed-128k")
    abstract void questionsAndAnswers(String content);


    //---------------------------------------------------------------------
    //                         Token Method
    //---------------------------------------------------------------------

    public String getAccessToken() {
        return getToken().getAccess_token();
    }

    @Override
    protected File getJsonFile() {
        return new File(System.getProperty("user.dir"), "baidu_token.json");
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


    public static class BaiduSseEventListener extends AnnotationEventListener {

        @OnMessage("#{$data$.is_end}")
        public void clear(Message message) {
            Console.printlnMulberry("\n");
            DelayedOutput.clearOutputLength();
        }

        @OnMessage
        public void printContext(@Param("#{$data$.result}") String context) {
            DelayedOutput.output(context, 70, 20);
        }
    }

}
