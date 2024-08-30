package io.github.lucklike.luckyclient.api.baiduai;

import com.luckyframework.httpclient.core.meta.Request;
import com.luckyframework.httpclient.core.meta.Response;
import com.luckyframework.httpclient.generalapi.token.JsonFileTokenManager;
import com.luckyframework.httpclient.proxy.context.MethodContext;
import com.luckyframework.httpclient.proxy.mock.Mock;
import com.luckyframework.httpclient.proxy.mock.MockResponse;
import com.luckyframework.httpclient.proxy.mock.SseData;
import io.github.lucklike.httpclient.annotation.HttpClientComponent;
import io.github.lucklike.luckyclient.api.util.AI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;


/**
 * 百度千帆大模型平台API
 */
@HttpClientComponent
public interface BaiduAI extends AI {

    /**
     * 获取访问Token
     *
     * @return 访问Token
     */
    Token getToken();

    /**
     * 问答接口
     *
     * @param content 提问内容
     */
    @Mock(mockResp = "#{#questionsAndAnswersMock($mc$, $req$)}", cache = false)
    void questionsAndAnswers(String content);

    static Response questionsAndAnswersMock(MethodContext context, Request request) throws IOException {
        return MockResponse
                .create()
                .header("Content-Type", "text/event-stream; charset=utf-8")
                .sse(SseData.create()
                        .addSection(SseData.section()
                                .comment("测试数据")
                                .id("questionsAndAnswers")
                                .event("SSE")
                                .retry("5000")
                                .data("{\"is_end\": false, \"result\": \"\"}"))
                        .addData("{\"is_end\": false, \"result\": \"ARGS: " + context.getArguments()[0] + "\\n\"}")
                        .addData("{\"is_end\": false, \"result\": \"URL: [" + request.getRequestMethod() + "] " + request.getUrl() + "\\n\"}")
                        .addData("{\"is_end\": false, \"result\": \"你好呀\"}")
                        .addData("{\"is_end\": false, \"result\": \",我是Mock出来的数据\"}")
                        .addData("{\"is_end\": false, \"result\": \"。我用于为方法questionsAndAnswers提供测试数据!\"}")
                        .addData("{\"is_end\": true, \"result\": \"! \"}")
                );
    }

    /**
     * Token管理器
     */
    @Component("baiduAITokenManager")
    class BaiduAITokenManager extends JsonFileTokenManager<Token> {

        private final BaiduAI baiduAI;
        private final File jsonFile;


        public BaiduAITokenManager(BaiduAI baiduAI, @Value("${user.dir}") String userDir) {
            this.baiduAI = baiduAI;
            this.jsonFile = new File(userDir + File.separator + "baidu_token.json");
        }

        public String getAccessToken() {
            return getToken().getAccess_token();
        }

        @Override
        protected Token refreshToken(Token oldToken) {
            Token token = baiduAI.getToken();
            token.generateExpiresTime();
            return token;
        }

        @Override
        protected boolean isExpires(Token token) {
            return token.isExpires();
        }


        @Override
        protected File getJsonFile() {
            return jsonFile;
        }
    }
}



