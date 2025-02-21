package io.github.lucklike.luckyclient.api.cairh.nacos;

import com.luckyframework.httpclient.generalapi.describe.Describe;
import com.luckyframework.httpclient.generalapi.token.MemoryTokenManager;
import com.luckyframework.httpclient.proxy.annotations.Post;
import com.luckyframework.httpclient.proxy.annotations.StaticJsonBody;
import io.github.lucklike.luckyclient.api.cairh.openapi.Token;

import java.util.Date;

@NacosClient(value = "base-backend", contextPath = "basedata")
public abstract class CrhTokenApi extends MemoryTokenManager<Token> {

    @Post("/authless/token")
    @Describe(name = "获取访问Token", needToken = false)
    @StaticJsonBody("#{#read('classpath:crh.json')}")
    public abstract Token token();


    //---------------------------------------------------------------------------
    //                              Token Manager
    //---------------------------------------------------------------------------

    public String getAccessToken() {
        return getToken().getAccessToken();
    }

    @Override
    protected Token refreshToken(Token oldToken) {
        return token();
    }

    @Override
    protected boolean isExpires(Token token) {
        return token.getExpiresAt().before(new Date());
    }
}
