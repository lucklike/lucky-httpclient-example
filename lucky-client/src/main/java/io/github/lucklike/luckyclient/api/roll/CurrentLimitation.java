package io.github.lucklike.luckyclient.api.roll;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.luckyframework.httpclient.proxy.plugin.ExecuteMeta;
import com.luckyframework.httpclient.proxy.plugin.Plugin;
import com.luckyframework.httpclient.proxy.plugin.ProxyDecorator;
import com.luckyframework.httpclient.proxy.plugin.ProxyPlugin;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 基于QPS的限流注解
 *
 * @author fukang
 * @version 1.0.0
 * @date 2025/1/28 03:18
 */
@Target({ElementType.METHOD, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Plugin(pluginClass = CurrentLimitation.LimitPlugin.class)
public @interface CurrentLimitation {

    /**
     * 限制的QPS，默认值10
     */
    int limitQPS() default 10;

    class LimitPlugin implements ProxyPlugin {

        private final AtomicBoolean invited = new AtomicBoolean(false);

        @Override
        public void init(ExecuteMeta meta) {
            if (invited.compareAndSet(false, true)) {
                CurrentLimitation limitationAnn = meta.getMetaContext().getMergedAnnotationCheckParent(CurrentLimitation.class);
                List<FlowRule> rules = new ArrayList<>();
                FlowRule rule = new FlowRule();
                rule.setResource(meta.getTargetClass().getName());
                rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
                rule.setCount(limitationAnn.limitQPS());
                rules.add(rule);
                FlowRuleManager.loadRules(rules);
            }
        }

        @Override
        public Object decorate(ProxyDecorator decorator) throws Throwable {
            ExecuteMeta meta = decorator.getMeta();
            Method method = meta.getMethod();
            while (true) {
                try (Entry ignored = SphU.entry(meta.getTargetClass().getName())) {
                    return decorator.proceed();
                } catch (BlockException ex) {
                    System.out.println(method.getName() + "被限流，等待资源中.....");
                    try {
                        Thread.sleep(1200L);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }
}



