package io.github.lucklike.discovery.nacos.cairh.api;

import com.luckyframework.httpclient.generalapi.describe.Describe;
import com.luckyframework.httpclient.generalapi.token.JsonFileTokenManager;
import com.luckyframework.httpclient.generalapi.token.UseTokenManager;
import com.luckyframework.httpclient.proxy.annotations.Post;
import com.luckyframework.httpclient.proxy.annotations.ResourceJson;
import com.luckyframework.httpclient.proxy.annotations.StaticJsonBody;
import io.github.lucklike.httpclient.discovery.HttpClient;

import java.io.File;
import java.util.Date;

//@NacosClient(value = "base-backend", contextPath = "basedata")
@HttpClient(service = "cpe-base-gateway-server", path = "basedata")
public interface CrhTokenApi {

    @Post("/authless/token")
    @Describe(name = "获取访问Token", tokenApi = true)
    @ResourceJson("classpath:crh.json")
    @UseTokenManager("${user.dir}/crh_nacos_token.json")
    Token token();
}
