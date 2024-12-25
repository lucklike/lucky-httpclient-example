package io.github.lucklike.luckyclient.api.roll;

import com.luckyframework.httpclient.proxy.annotations.Condition;
import com.luckyframework.httpclient.proxy.annotations.Get;
import com.luckyframework.httpclient.proxy.annotations.QueryParam;
import com.luckyframework.httpclient.proxy.annotations.RespConvert;
import com.luckyframework.httpclient.proxy.annotations.Retryable;
import com.luckyframework.httpclient.proxy.annotations.SocksProxy;
import com.luckyframework.httpclient.proxy.annotations.StaticQuery;
import com.luckyframework.httpclient.proxy.annotations.Wrapper;
import io.github.lucklike.httpclient.annotation.HttpClient;

import java.util.Map;

/**
 * 油价查询API
 */
@HttpClient
@Condition(assertion = "#{$status$ != 200}", exception = "【ROLL】油价查询接口调用异常，响应码：'#{$status$}'")
@Condition(assertion = "#{$body$.code != 1}", exception = "【ROLL】油价查询接口调用异常，状态码：'#{$body$.code}', 错误信息：#{$body$.msg}")
@StaticQuery({"app_id=${ROLL.AppID}", "app_secret=${ROLL.AppSecret}"})
public interface OilPriceApi {

    @Wrapper("#{$this$.query(p).t92}")
    String query92(String p);

    @Wrapper("#{$this$.query0(province).data}")
    Map<String, Object> query(String province);

    @SocksProxy(ip = "118.25.42.139", port = "8882", username = "marry", password = "pass123")
    @Retryable(retryCount = 10, waitMillis = 2000L)
    @Get("https://www.mxnzp.com/api/oil/search")
    Map<String, Object> query0(@QueryParam String province);

}
