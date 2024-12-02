package io.github.lucklike.luckyclient.api.cairh;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.luckyframework.httpclient.generalapi.describe.Describe;
import com.luckyframework.httpclient.generalapi.token.MemoryTokenManager;
import com.luckyframework.httpclient.proxy.annotations.Post;
import com.luckyframework.httpclient.proxy.annotations.PropertiesJsonObject;
import io.github.lucklike.luckyclient.api.cairh.annotations.CRHApi;
import lombok.Data;

import java.util.Date;


@CRHApi(name = "tokenApi")
public abstract class CrhTokenApi extends MemoryTokenManager<Token> {

    @Describe(isTokenApi = true)
    @PropertiesJsonObject({
            "secret_key=${cairh.openapi.secretKey}",
            "app_id=${cairh.openapi.appId}"
    })
    @Post("/openapi/authless/token")
    abstract Token token();


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

@Data
class Token {
    private String accessToken;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date expiresAt;
}
