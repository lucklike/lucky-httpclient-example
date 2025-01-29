package io.github.lucklike.luckyclient.api.roll;


import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.luckyframework.httpclient.proxy.annotations.Get;
import com.luckyframework.httpclient.proxy.annotations.QueryParam;
import com.luckyframework.httpclient.proxy.annotations.RespConvert;
import com.luckyframework.httpclient.proxy.annotations.Wrapper;
import com.luckyframework.httpclient.proxy.context.ClassContext;
import com.luckyframework.httpclient.proxy.context.MethodContext;
import com.luckyframework.httpclient.proxy.spel.hook.Lifecycle;
import com.luckyframework.httpclient.proxy.spel.hook.callback.Callback;
import com.luckyframework.reflect.Param;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 支持限流功能的流量查询接口
 */
@OilHttpClient
@CurrentLimitation(qps = 12)
public interface CurrentLimitationOilPriceApi {

    /**
     * 基于原始油价家口包装的支持限流的查询方法
     *
     * @param province 省份
     * @return 油价信息
     */
    @CurrentLimitation(qps = 1)
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
