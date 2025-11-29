package io.github.lucklike.luckyclient.api.cairh.openapi;

import com.luckyframework.httpclient.generalapi.describe.Describe;
import com.luckyframework.httpclient.generalapi.describe.TokenApi;
import com.luckyframework.httpclient.generalapi.token.JsonFileTokenManager;
import com.luckyframework.httpclient.proxy.annotations.JsonParam;
import com.luckyframework.httpclient.proxy.annotations.Post;
import com.luckyframework.httpclient.proxy.annotations.PropertiesJson;
import io.github.lucklike.luckyclient.api.cairh.annotations.CRHApi;

import java.io.File;
import java.util.Date;
import java.util.Map;

@CRHApi(project = "openapi")
public abstract class CrhOpenApi extends JsonFileTokenManager<Token> {

    @Describe("产品查询")
    @Post("/common/queryProduct")
    public abstract Map<String, Object> queryProduct(@JsonParam int productNo);

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

    //    @Override
    protected File getJsonFile() {
        return new File(System.getProperty("user.dir"), "cairh_token.json");
    }

}

