package io.github.lucklike.luckyclient.api.plugin;

import com.luckyframework.httpclient.proxy.plugin.ExecuteMeta;
import com.luckyframework.httpclient.proxy.plugin.ProxyDecorator;
import com.luckyframework.httpclient.proxy.plugin.ProxyPlugin;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author fukang
 * @version 1.0.0
 * @date 2025/1/30 01:45
 */
@Order(5)
@Component
public class MyPlugin implements ProxyPlugin {

    @Override
    public Object decorate(ProxyDecorator decorator) throws Throwable {
        System.out.println("MyPlugin -- Start");
        String a1 = "MyPlugin--" + decorator.getMeta().getArgs()[0];
        String a2 = "MyPlugin--" + decorator.getMeta().getArgs()[1];
        Object result = decorator.proceed(a1, a2);
        System.out.println("MyPlugin -- End");
        return result;
    }

    @Override
    public boolean match(ExecuteMeta meta) {
        return meta.getMetaContext().isAnnotatedCheckParent(UseMyPlugin.class);
    }
}
