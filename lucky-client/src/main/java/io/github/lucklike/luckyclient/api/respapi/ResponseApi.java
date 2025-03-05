package io.github.lucklike.luckyclient.api.respapi;

import com.luckyframework.httpclient.core.meta.Response;
import com.luckyframework.httpclient.proxy.annotations.DomainName;
import com.luckyframework.httpclient.proxy.annotations.Get;

@DomainName("http://localhost")
public interface ResponseApi {

    @Get("/response/test")
    Response response();
}
