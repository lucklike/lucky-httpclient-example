package io.github.lucklike.luckyclient.api.abstractapi;

import com.luckyframework.common.Console;
import com.luckyframework.httpclient.core.meta.Request;
import com.luckyframework.httpclient.generalapi.describe.ApiDescribe;
import com.luckyframework.httpclient.generalapi.describe.TokenApi;
import com.luckyframework.httpclient.generalapi.token.JsonFileTokenManager;
import com.luckyframework.httpclient.proxy.annotations.Post;
import com.luckyframework.httpclient.proxy.annotations.PrintLogProhibition;
import com.luckyframework.httpclient.proxy.annotations.PropertiesJson;
import com.luckyframework.httpclient.proxy.annotations.Retryable;
import com.luckyframework.httpclient.proxy.annotations.StaticQuery;
import com.luckyframework.httpclient.proxy.spel.hook.Lifecycle;
import com.luckyframework.httpclient.proxy.spel.hook.callback.Callback;
import com.luckyframework.httpclient.proxy.sse.Sse;
import io.github.lucklike.httpclient.discovery.HttpClient;
import io.github.lucklike.luckyclient.api.baiduai.BaiduAISseEventListener;
import io.github.lucklike.luckyclient.api.baiduai.Token;

import java.io.File;
import java.util.Scanner;

/**
 * @author fukang
 * @version 1.0.0
 * @date 2024/9/4 20:48
 */
@Retryable
@PrintLogProhibition
@HttpClient(beanId = "annBaiduAI", value = "https://aip.baidubce.com")
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
    static void paramAddCallback(ApiDescribe apiDesc, Request request, BaiduAI baiduAI) {
        if (!apiDesc.isTokenApi()) {
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
    @Sse(listenerClass = BaiduAISseEventListener.class)
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

}
