package io.github.lucklike.luckyclient.api.abstractapi;

import com.luckyframework.common.ConfigurationMap;
import com.luckyframework.common.Console;
import com.luckyframework.httpclient.generalapi.token.JsonFileTokenManager;
import com.luckyframework.httpclient.proxy.annotations.Condition;
import com.luckyframework.httpclient.proxy.annotations.Post;
import com.luckyframework.httpclient.proxy.annotations.PrintLogProhibition;
import com.luckyframework.httpclient.proxy.annotations.PrintResponseLog;
import com.luckyframework.httpclient.proxy.annotations.PropertiesJsonObject;
import com.luckyframework.httpclient.proxy.annotations.StaticHeader;
import com.luckyframework.httpclient.proxy.annotations.StaticQuery;
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
@PrintLogProhibition
@HttpClient(name = "BAI-DU-AI", value = "https://aip.baidubce.com")
@StaticHeader("Content-Type: application/json")
@StaticQuery("@if(#{$method$.getName() != 'token'}): access_token=#{$this$.getAccessToken()}")
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
    //                          Api Method
    //---------------------------------------------------------------------


    @StaticQuery({
        "grant_type=client_credentials",
        "client_id=${baidu.API.APIKey}",
        "client_secret=${baidu.API.SecretKey}"
    })
    @Post("/oauth/2.0/token")
    abstract Token token();

    @PropertiesJsonObject({
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
            DelayedOutput.output(result, 80, 50);
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
        return new File(System.getProperty("user.dir") + File.separator + "baidu_token.json");
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
