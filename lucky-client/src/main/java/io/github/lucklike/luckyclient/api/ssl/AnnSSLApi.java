package io.github.lucklike.luckyclient.api.ssl;

import com.luckyframework.httpclient.proxy.annotations.Get;
import com.luckyframework.httpclient.proxy.annotations.SSL;
import io.github.lucklike.httpclient.annotation.HttpClient;

/**
 * SSL认证相关的API‘
 * @author fukang
 * @version 1.0.0
 * @date 2024/8/3 18:14
 */
@SSL(keyStore = "test", trustStore = "test2")
@HttpClient("https://localhost:8888/SpringBoot2Demo")
public interface AnnSSLApi {


    @Get("/demo/current")
    String test();
}
