package io.github.lucklike.common.plugn;

import com.luckyframework.common.StopWatch;
import com.luckyframework.httpclient.proxy.plugin.ProxyDecorator;
import com.luckyframework.httpclient.proxy.plugin.ProxyPlugin;

/**
 * @author fukang
 * @version 1.0.0
 * @date 2025/3/13 23:42
 */
public class TimeStatisticsPlugin implements ProxyPlugin {
    @Override
    public Object decorate(ProxyDecorator decorator) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start(decorator.getMeta().getMetaContext().getSimpleSignature());
        Object result = decorator.proceed();
        stopWatch.stopWatch();
        System.out.println(stopWatch.prettyPrintFormat());
        return result;
    }
}
