package io.github.lucklike.luckyclient.api.websocket.kdxf;

import com.luckyframework.httpclient.generalapi.token.MemoryTokenManager;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Token管理器
 */
@Component
@AllArgsConstructor
public class KdxfTokenManager extends MemoryTokenManager<KdxfTokenManager.Token> {

    private Configuration config;
    private HttpApi httpApi;

    @Override
    protected Token refreshToken(Token oldToken) {
        return httpApi.token(config.getAppId(), config.getAppKey(), config.getAppSecret());
    }

    @Override
    protected boolean isExpires(Token token) {
        return token.isExpired();
    }

    /**
     * Token实体类
     */
    @Data
    public static class Token {

        private String accessToken;
        private Date expires;

        public boolean isExpired() {
            return new Date().after(expires);
        }

    }
}
