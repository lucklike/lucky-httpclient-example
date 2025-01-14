package io.github.lucklike.luckyclient.api.server.ann;

import com.luckyframework.httpclient.proxy.annotations.Get;
import com.luckyframework.httpclient.proxy.annotations.RespConvert;
import io.github.lucklike.entity.request.bytes.ByteJson;
import io.github.lucklike.httpclient.annotation.HttpClient;

/**
 * @author fukang
 * @version 1.0.0
 * @date 2025/1/15 01:17
 */
@HttpClient("http://localhost:8864")
public interface ByteJsonApi {

    @RespConvert("#{$body$.data}")
    @Get("/byte/json")
    byte[] byteJson();
}
