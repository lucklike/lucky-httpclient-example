package io.github.lucklike.luckyclient.api.baiduai;

import com.luckyframework.httpclient.generalapi.token.JsonFileTokenManager;
import io.github.lucklike.httpclient.annotation.HttpClientComponent;
import io.github.lucklike.luckyclient.api.util.AI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;


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
    void questionsAndAnswers(String content);


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



