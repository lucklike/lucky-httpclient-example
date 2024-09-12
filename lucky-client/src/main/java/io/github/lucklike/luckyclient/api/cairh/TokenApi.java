package io.github.lucklike.luckyclient.api.cairh;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.luckyframework.httpclient.generalapi.token.JsonFileTokenManager;
import com.luckyframework.httpclient.proxy.annotations.Post;
import com.luckyframework.httpclient.proxy.annotations.PropertiesJsonObject;
import io.github.lucklike.luckyclient.api.cairh.annotations.CRHApi;
import io.github.lucklike.luckyclient.api.cairh.annotations.NonToken;
import lombok.Data;

import java.io.File;
import java.util.Date;


@CRHApi
@NonToken
public abstract class TokenApi extends JsonFileTokenManager<Token> {

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
    protected File getJsonFile() {
        return new File(System.getProperty("user.dir"), "cairh_token.json");
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
