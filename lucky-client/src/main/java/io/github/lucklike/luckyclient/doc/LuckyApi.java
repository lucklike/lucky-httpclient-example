package io.github.lucklike.luckyclient.doc;

import com.luckyframework.httpclient.core.meta.RequestMethod;
import com.luckyframework.httpclient.proxy.annotations.BasicAuth;
import com.luckyframework.httpclient.proxy.annotations.CookieParam;
import com.luckyframework.httpclient.proxy.annotations.Get;
import com.luckyframework.httpclient.proxy.annotations.HttpRequest;
import com.luckyframework.httpclient.proxy.annotations.MethodParam;
import com.luckyframework.httpclient.proxy.annotations.MultiData;
import com.luckyframework.httpclient.proxy.annotations.Post;
import com.luckyframework.httpclient.proxy.annotations.PrintLog;
import com.luckyframework.httpclient.proxy.annotations.QueryParam;
import com.luckyframework.httpclient.proxy.annotations.StaticHeader;
import com.luckyframework.httpclient.proxy.annotations.URLEncoder;
import com.luckyframework.httpclient.proxy.annotations.URLEncoderQuery;
import com.luckyframework.httpclient.proxy.annotations.Url;
import io.github.lucklike.httpclient.annotation.HttpClientComponent;

@PrintLog
@HttpClientComponent
public interface LuckyApi {

    @StaticHeader("Accept: text/plain")
    @Get("http://localhost:8080/lucky/hello")
    String hello(@Url String method);

    @Post("http://localhost:8080/lucky/hello")
    @MultiData
    String text(String name, Integer age, String email);


}
