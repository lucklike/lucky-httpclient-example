package io.github.lucklike.luckyclient.api.roll;


import com.luckyframework.httpclient.proxy.annotations.Get;
import com.luckyframework.httpclient.proxy.annotations.QueryParam;

import java.util.Map;

/**
 * 支持限流功能的流量查询接口
 */
@OilHttpClient
public interface CurrentLimitationOilPriceApi {

    /**
     * 基于原始油价家口包装的支持限流的查询方法
     *
     * @param province 省份
     * @return 油价信息
     */
    @CurrentLimitation(limitQPS = 1)
    @Get("/api/oil/search")
    Map<String, Object> limitQuery(@QueryParam String province);

    /**
     * 原始油价查询方法
     *
     * @param province 省份
     * @return 油价信息
     */
    @Get("/api/oil/search")
    Map<String, Object> query(@QueryParam String province);
}
