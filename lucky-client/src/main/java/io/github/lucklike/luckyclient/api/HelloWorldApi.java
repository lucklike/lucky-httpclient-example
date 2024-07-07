package io.github.lucklike.luckyclient.api;

import com.luckyframework.httpclient.proxy.annotations.Get;
import io.github.lucklike.entity.response.Result;
import io.github.lucklike.httpclient.annotation.HttpClientComponent;

/**
 * @author fukang
 * @version 1.0.0
 * @date 2024/7/7 03:35
 */
@HttpClientComponent
public interface HelloWorldApi extends LuckyServerApi{

    @Get("/hello")
    String hello();

    @Get("/exception")
    Result<Object> exception();
}
