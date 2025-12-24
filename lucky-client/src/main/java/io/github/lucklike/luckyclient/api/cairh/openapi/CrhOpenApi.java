package io.github.lucklike.luckyclient.api.cairh.openapi;

import com.luckyframework.httpclient.generalapi.describe.Describe;
import com.luckyframework.httpclient.generalapi.describe.TokenApi;
import com.luckyframework.httpclient.generalapi.token.JsonFileTokenManager;
import com.luckyframework.httpclient.generalapi.token.UseTokenManager;
import com.luckyframework.httpclient.proxy.annotations.JsonParam;
import com.luckyframework.httpclient.proxy.annotations.Post;
import com.luckyframework.httpclient.proxy.annotations.PropertiesJson;
import io.github.lucklike.httpclient.annotation.EnvironmentJson;
import io.github.lucklike.luckyclient.api.cairh.annotations.CRHApi;

import java.io.File;
import java.util.Date;
import java.util.Map;

@CRHApi(project = "openapi")
public interface CrhOpenApi {

    @Describe("产品查询")
    @Post("/common/queryProduct")
    Map<String, Object> queryProduct(@JsonParam int productNo);

    @Describe("获取访问Token")
    @Post("/authless/token")
    @EnvironmentJson("cairh.openapi.token")
    @UseTokenManager("${user.dir}/cairh_token.json")
    Token token();
}

