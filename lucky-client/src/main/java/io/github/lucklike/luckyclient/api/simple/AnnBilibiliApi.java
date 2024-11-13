package io.github.lucklike.luckyclient.api.simple;

import com.luckyframework.httpclient.proxy.annotations.AutoRedirect;
import com.luckyframework.httpclient.proxy.annotations.Get;
import com.luckyframework.httpclient.proxy.annotations.RespConvert;
import com.luckyframework.httpclient.proxy.spel.SpELImport;
import io.github.lucklike.httpclient.annotation.HttpClient;

@AutoRedirect
@HttpClient
@SpELImport(BilibiliFunction.class)
public interface AnnBilibiliApi {

    @Get("http://www.bilibili.com")
    String index();
}
