package io.github.lucklike.luckyclient.api.cairh.nacos;

import com.luckyframework.httpclient.generalapi.describe.Describe;
import com.luckyframework.httpclient.generalapi.describe.TokenApi;
import com.luckyframework.httpclient.generalapi.token.MemoryTokenManager;
import com.luckyframework.httpclient.proxy.annotations.Post;
import com.luckyframework.httpclient.proxy.annotations.PropertiesJson;
import io.github.lucklike.luckyclient.api.cairh.openapi.Token;

import java.util.Date;

@NacosClient("front")
public abstract class CrhTokenApi extends MemoryTokenManager<Token> {

    @Describe("获取访问Token")
    @PropertiesJson({
        "secret_key=${cairh.openapi.secretKey}",
        "app_id=${cairh.openapi.appId}"
    })
    @TokenApi
    @Post("/authless/token")
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
