package io.github.lucklike.luckyclient.api.server.nacos;

import com.luckyframework.httpclient.proxy.annotations.Get;
import com.luckyframework.httpclient.proxy.annotations.PrintLogProhibition;
import com.luckyframework.httpclient.proxy.annotations.QueryParam;
import io.github.lucklike.httpclient.discovery.HttpClient;

/**
 * @author fukang
 * @version 1.0.0
 * @date 2025/2/18 23:11
 */
@PrintLogProhibition
@HttpClient(service = "lucky-server")
public interface LuckyNacosServerApi {

    @Get("hello")
    String hello();

    @Get("/user/get")
    String getUser(@QueryParam int idLength);
}
