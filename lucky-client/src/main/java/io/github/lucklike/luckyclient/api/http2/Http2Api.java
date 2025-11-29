package io.github.lucklike.luckyclient.api.http2;

import com.luckyframework.httpclient.proxy.annotations.Get;
import com.luckyframework.httpclient.proxy.annotations.HttpExec;
import io.github.lucklike.httpclient.discovery.HttpClient;

/**
 * @author fukang
 * @version 1.0.0
 * @date 2025/9/6 04:19
 */
@HttpClient("https://www.h2check.org/#http2.akamai.com")
public interface Http2Api {

    @Get
    String index();
}
