package io.github.lucklike.luckyclient.api.roll;

import com.luckyframework.httpclient.proxy.annotations.Branch;
import com.luckyframework.httpclient.proxy.annotations.Get;
import com.luckyframework.httpclient.proxy.annotations.QueryParam;
import com.luckyframework.httpclient.proxy.annotations.RespConvert;
import com.luckyframework.httpclient.proxy.annotations.StaticQuery;
import io.github.lucklike.httpclient.annotation.HttpClient;

import java.util.Map;

/**
 * 油价查询API
 */
@HttpClient
@RespConvert(
    conditions = {
            @Branch(assertion = "#{$status$ != 200}", exception = "【ROLL】油价查询接口调用异常，响应码：'#{$status$}'"),
            @Branch(assertion = "#{$body$.code != 1}", exception = "【ROLL】油价查询接口调用异常，状态码：'#{$body$.code}', 错误信息：#{$body$.msg}")
    })
@StaticQuery({"app_id=${ROLL.AppID}", "app_secret=${ROLL.AppSecret}"})
public interface OilPriceApi {


    @RespConvert("#{$body$.data}")
    @Get("https://www.mxnzp.com/api/oil/search")
    Map<String, Object> query(@QueryParam String province);
}
