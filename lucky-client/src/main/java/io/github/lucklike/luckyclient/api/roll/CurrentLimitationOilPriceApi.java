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
public interface CurrentLimitationOilPriceApi {

    /**
     * 基于原始油价家口包装的支持限流的查询方法
     *
     * @param p 省份
     * @return 油价信息
     */
    @Wrapper(fun = "limitQueryWrapper")
    Map<String, Object> limitQuery(String p);

    /**
     * 原始油价查询方法
     *
     * @param province 省份
     * @return 油价信息
     */
    @RespConvert("#{$body$.data}")
    @Get("/api/oil/search")
    Map<String, Object> query(@QueryParam String province);

    //-------------------------------------------------------------------------
    //                      Callback & Wrapper Method
    //-------------------------------------------------------------------------

    /**
     * 初始化一个类级别的限流器管理器
     *
     * @param context 类上下文
     */
    @Callback(lifecycle = Lifecycle.CLASS)
    static void initFlowRules(ClassContext context) {
        List<FlowRule> rules = new ArrayList<>();
        FlowRule rule = new FlowRule();
        rule.setResource(context.getCurrentAnnotatedElement().getName());
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        rule.setCount(1);
        rules.add(rule);
        FlowRuleManager.loadRules(rules);
    }

    /**
     * 查询包装器逻辑
     *
     * @param mc       方法上下文
     * @param api      当前代理对象实例
     * @param province 方法参数
     * @return 油价信息
     * @throws InterruptedException 限流等待时可能出现的异常
     */
    static Map<String, Object> limitQueryWrapper(MethodContext mc,
                                                 CurrentLimitationOilPriceApi api,
                                                 @Param("#{p0}") String province
    ) throws InterruptedException {
        while (true) {
            try (Entry ignored = SphU.entry(mc.getClassContext().getCurrentAnnotatedElement().getName())) {
                return api.query(province);
            } catch (BlockException ex) {
                System.out.println(mc.getCurrentAnnotatedElement().getName() + "被限流，等待资源中.....");
                Thread.sleep(1500L);
            }
        }
    }
}
