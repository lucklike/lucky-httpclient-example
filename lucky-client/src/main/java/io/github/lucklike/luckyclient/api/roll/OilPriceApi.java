package io.github.lucklike.luckyclient.api.roll;

import com.luckyframework.httpclient.core.meta.Response;
import com.luckyframework.httpclient.proxy.annotations.Get;
import com.luckyframework.httpclient.proxy.annotations.QueryParam;
import com.luckyframework.httpclient.proxy.annotations.Retryable;
import com.luckyframework.httpclient.proxy.annotations.Wrapper;

import java.util.Map;

/**
 * 油价查询API
 */
@OilHttpClient
public interface OilPriceApi {

    @Wrapper("#{$this$.query(p).t92}")
    String query92(String p);

    //    @SocksProxy(host = "118.25.42.139", port = "8882", username = "marry", password = "pass123")
    @Retryable(retryCount = 10, waitMillis = 2000L)
    @Get("https://www.mxnzp.com/api/oil/search")
    Map<String, Object> query(@QueryParam String province);

    @Get("https://www.mxnzp.com/api/oil/search")
    Map<String, Object> query1(@QueryParam String province);

    static Map<String, Object> oilPriceApi$Convert(Response response) {
        return (Map<String, Object>) response.getMapResult().get("data");
    }


}

