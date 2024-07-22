package io.github.lucklike.luckyclient.api.simple;

import com.luckyframework.httpclient.proxy.annotations.AutoRedirect;
import com.luckyframework.httpclient.proxy.annotations.Get;
import io.github.lucklike.httpclient.annotation.HttpClient;

@AutoRedirect
@HttpClient
public interface AnnBilibiliApi {

    @Get("http://www.bilibili.com")
    String index();
}
