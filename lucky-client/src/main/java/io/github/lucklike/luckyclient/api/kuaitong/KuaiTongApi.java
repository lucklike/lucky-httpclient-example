package io.github.lucklike.luckyclient.api.kuaitong;

import com.luckyframework.common.StringUtils;
import com.luckyframework.httpclient.generalapi.token.LocalJsonFileTokenManager;
import io.github.lucklike.httpclient.configapi.LocalConfigHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 快瞳API
 */
@LocalConfigHttpClient
public interface KuaiTongApi {

    /**
     * 获取访问Token
     *
     * @return 访问Token
     */
    Token getAccessToken();

    /**
     * 身份证OCR识别
     *
     * @param idCardPath 身份证图片路径
     * @return 身份证信息
     */
    IdentityInfo identityCardOcr(String idCardPath);


    /**
     * 自动添加Token的拦截器，支持过期自动续签
     */
    @Component("kuaiTongTokenManager")
    class KuaiTongTokenManager extends LocalJsonFileTokenManager<Token> {


        @Resource
        private KuaiTongApi api;

        @Value("${user.dir}")
        private String userDir;

        @Override
        protected Token refreshToken(Token oldToken) {
            Token token = api.getAccessToken();
            token.generateExpiresTime();
            return token;
        }

        @Override
        protected boolean isExpires(Token token) {
            return token.isExpires();
        }

        @Override
        protected String getResourceLocation() {
            return StringUtils.format("file:{}/kt_token.json", userDir);
        }

        @Override
        protected Class<Token> getTokenClass() {
            return Token.class;
        }
    }

}
