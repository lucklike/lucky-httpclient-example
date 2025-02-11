package io.github.lucklike.luckyclient.api.plugin;

import com.luckyframework.httpclient.proxy.plugin.After;
import com.luckyframework.httpclient.proxy.plugin.Around;
import com.luckyframework.httpclient.proxy.plugin.Before;
import com.luckyframework.httpclient.proxy.plugin.ExecuteMeta;
import com.luckyframework.httpclient.proxy.plugin.ProxyDecorator;
import com.luckyframework.reflect.Param;
import io.github.lucklike.httpclient.plugin.HttpPlugin;
import org.springframework.stereotype.Component;

@HttpPlugin
@Component
public class MyPlugin3 {

    //    private static final String USER_MY_PLUGIN = "#{#hasAnncp($mec$, T(io.github.lucklike.luckyclient.api.plugin.UseMyPlugin))}";
    private static final String USER_MY_PLUGIN = "#{T(io.github.lucklike.luckyclient.api.plugin.PluginApi) == $class$}";


    @Before(USER_MY_PLUGIN)
    public void before(ExecuteMeta meta) {
        System.out.println("Before plugin");
    }

    @After(USER_MY_PLUGIN)
    public void after(ExecuteMeta meta) {
        System.out.println("After plugin");
    }

    @Around(USER_MY_PLUGIN)
    public Object around(ProxyDecorator decorator, @Param("#{$args$}") Object[] args) throws Throwable {
        String a1 = "Around--" + args[0];
        String a2 = "Around--" + args[1];
        return decorator.proceed(a1, a2);
    }

    @Around(USER_MY_PLUGIN)
    public Object around1(ProxyDecorator decorator) throws Throwable {
        // 获取执行元数据
        ExecuteMeta meta = decorator.getMeta();
        String method = meta.getMethod().getName();

        // 记录开始时间
        long start = System.currentTimeMillis();
        System.out.println("Start [" + method + "]");

        // 执行代理方法
        Object proceed = decorator.proceed();

        // 记录结束时间
        System.out.println("End [" + method + "]");
        long end = System.currentTimeMillis();
        System.out.println("Around Running [" + method + " Execution time: " + (end - start) + "ms]");
        return proceed;
    }

}
