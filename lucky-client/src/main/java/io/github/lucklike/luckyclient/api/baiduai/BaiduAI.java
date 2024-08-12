package io.github.lucklike.luckyclient.api.baiduai;

import com.luckyframework.common.StringUtils;
import io.github.lucklike.httpclient.annotation.HttpClientComponent;
import io.github.lucklike.luckyclient.api.util.AI;
import io.github.lucklike.luckyclient.api.util.token.JsonFileTokenManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


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
     * @param content 提问内容
     */
    void questionsAndAnswers(String content);


    /**
     * Token管理器
     */
    @Component("baiduAITokenManager")
    class BaiduAITokenManager extends JsonFileTokenManager<Token> {

        @Resource
        private BaiduAI baiduAI;

        @Value("${user.dir}")
        private String userDir;

        @Override
        protected String getJsonFilePath() {
            return StringUtils.format("file:{}/baidu_token.json", userDir);
        }

        @Override
        protected Token initToken() {
            Token token = baiduAI.getToken();
            token.generateExpiresTime();
            return token;
        }

        @Override
        protected boolean isExpires(Token token) {
            return token.isExpires();
        }

        @Override
        protected Class<Token> getTokenClass() {
            return Token.class;
        }
    }
}



