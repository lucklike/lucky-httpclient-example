package io.github.lucklike.luckyclient.api.mainfun;

import com.luckyframework.common.ConfigurationMap;
import com.luckyframework.common.Console;
import com.luckyframework.common.StringUtils;
import com.luckyframework.common.Table;
import com.luckyframework.httpclient.generalapi.token.JsonFileTokenManager;
import com.luckyframework.httpclient.proxy.annotations.DomainName;
import com.luckyframework.httpclient.proxy.annotations.Post;
import com.luckyframework.httpclient.proxy.annotations.PropertiesJson;
import com.luckyframework.httpclient.proxy.annotations.Retryable;
import com.luckyframework.httpclient.proxy.annotations.StaticHeader;
import com.luckyframework.httpclient.proxy.annotations.StaticQuery;
import com.luckyframework.httpclient.proxy.sse.Event;
import com.luckyframework.httpclient.proxy.sse.EventListener;
import com.luckyframework.httpclient.proxy.sse.Sse;
import com.luckyframework.httpclient.proxy.sse.standard.Message;
import com.luckyframework.httpclient.proxy.sse.standard.StandardEventListener;
import io.github.lucklike.luckyclient.api.baiduai.Token;
import io.github.lucklike.luckyclient.api.util.DelayedOutput;

import java.io.File;
import java.util.Scanner;

import static io.github.lucklike.luckyclient.api.mainfun.BaiduAIMain.CONFIG_MAP;

/**
 * @author fukang
 * @version 1.0.0
 * @date 2024/9/4 20:48
 */
@Retryable(retryCount = "5", waitMillis = "1000")
@DomainName("https://aip.baidubce.com")
@StaticHeader("Content-Type: application/json")
@StaticQuery("@if(#{$method$.getName() != 'token'}): access_token=#{$this$.getAccessToken()}")
public abstract class BaiduAI extends JsonFileTokenManager<Token> implements EventListener {

    private final File tokenFile;
    private int outputMaxLength = 80;
    private int outputDelayTime = 50;

    {
        tokenFile = CONFIG_MAP.getEntry("BAIDU.API.TOKEN_FILE", File.class);

        String maxLength = CONFIG_MAP.getStringOrDefault("BAIDU.OUT_PUT.MAX_LENGTH", "");
        String delayTime = CONFIG_MAP.getStringOrDefault("BAIDU.OUT_PUT.DELAY_TIME", "");

        if (StringUtils.hasText(maxLength)) {
            outputMaxLength = Integer.parseInt(maxLength);
        }

        if (StringUtils.hasText(delayTime)) {
            outputDelayTime = Integer.parseInt(delayTime);
        }

        Console.printlnGreen("----------------------------------欢迎使用百度AI--------------------------------------");
        Console.printlnGreen("");
        Table table = new Table();
        table.styleSeven();
        table.addHeader("CONF-KEY", "CONF-VALUE");
        table.addDataRow("Config File", CONFIG_MAP.getString("BAIDU.API.CONFIG.FILE"));
        table.addDataRow("Token File", CONFIG_MAP.getString("BAIDU.API.TOKEN_FILE"));
        table.addDataRow("Output Max Length", outputMaxLength);
        table.addDataRow("Output Delay Time", outputDelayTime);
        Console.printlnGreen(table.format());

    }

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
            "client_id=``#{BAIDU.API.API_KEY}``",
            "client_secret=``#{BAIDU.API.SECRET_KEY}``"
    })
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
        return tokenFile;
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

    class BaiduAISseEventListener extends StandardEventListener {

        @Override
        public void onMessage(Event<Message> event) {
            Message message = event.getMessage();
            ConfigurationMap confMap = message.jsonDataToMap();
            if (!confMap.getBoolean("is_end")) {
                String result = confMap.getString("result");
                DelayedOutput.output(result, outputMaxLength, outputDelayTime);
            } else {
                Console.printlnMulberry("\n");
                DelayedOutput.clearOutputLength();
            }
        }
    }
}
