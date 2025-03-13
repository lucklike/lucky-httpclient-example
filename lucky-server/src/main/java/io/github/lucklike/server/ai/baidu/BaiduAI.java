package io.github.lucklike.server.ai.baidu;

import com.luckyframework.httpclient.core.meta.ConfigurationMapBodyObjectFactory;
import com.luckyframework.httpclient.core.meta.Request;
import com.luckyframework.httpclient.generalapi.describe.DescribeFunction;
import com.luckyframework.httpclient.generalapi.describe.TokenApi;
import com.luckyframework.httpclient.generalapi.token.JsonFileTokenManager;
import com.luckyframework.httpclient.proxy.annotations.Async;
import com.luckyframework.httpclient.proxy.annotations.JsonParam;
import com.luckyframework.httpclient.proxy.annotations.Post;
import com.luckyframework.httpclient.proxy.annotations.PrintLog;
import com.luckyframework.httpclient.proxy.annotations.Retryable;
import com.luckyframework.httpclient.proxy.context.MethodContext;
import com.luckyframework.httpclient.proxy.spel.hook.Lifecycle;
import com.luckyframework.httpclient.proxy.spel.hook.callback.Callback;
import com.luckyframework.httpclient.proxy.sse.EventListener;
import com.luckyframework.httpclient.proxy.sse.Sse;
import io.github.lucklike.httpclient.discovery.HttpClient;

import java.io.File;

import static com.luckyframework.httpclient.core.meta.ContentType.APPLICATION_JSON;
import static com.luckyframework.httpclient.core.serialization.SerializationConstant.JSON_SCHEME;

/**
 * @author fukang
 * @version 1.0.0
 * @date 2024/9/4 20:48
 */
@PrintLog
@Retryable
@HttpClient("https://aip.baidubce.com")
public abstract class BaiduAI extends JsonFileTokenManager<Token> {

    //---------------------------------------------------------------------
    //                         Callback Method
    //---------------------------------------------------------------------

    @Callback(lifecycle = Lifecycle.REQUEST_INIT)
    static void paramAddCallback(MethodContext context, Request request, BaiduAI baiduAI) {
        if (DescribeFunction.needToken(context)) {
            request.addQueryParameter("access_token", baiduAI.getAccessToken());
            if (context.getCurrentAnnotatedElement().getName().equals("questionsAndAnswers")) {
                ConfigurationMapBodyObjectFactory bodyObjectFactory = ConfigurationMapBodyObjectFactory.of(JSON_SCHEME, APPLICATION_JSON);
                bodyObjectFactory.addProperty("messages[0].role", "user");
                bodyObjectFactory.addProperty("stream", true);
                request.setContentType(APPLICATION_JSON);
                request.setBodyFactory(bodyObjectFactory);
            }
        } else {
            request.addQueryParameter("grant_type", "client_credentials");
            request.addQueryParameter("client_id", context.nestParseExpression("${baidu.API.APIKey}"));
            request.addQueryParameter("client_secret", context.nestParseExpression("${baidu.API.SecretKey}"));
        }
    }

    //---------------------------------------------------------------------
    //                          Api Method
    //---------------------------------------------------------------------


    @TokenApi
    @Post("/oauth/2.0/token")
    abstract Token token();


    @Sse(async = true, executor = "callback-pool")
    @Post("/rpc/2.0/ai_custom/v1/wenxinworkshop/chat/ernie-speed-128k")
    public abstract void questionsAndAnswers(@JsonParam("messages[0].content") String content, EventListener listener);


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

}
