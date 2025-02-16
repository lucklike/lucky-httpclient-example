package io.github.lucklike.luckyclient.api.plugin;

import com.luckyframework.httpclient.proxy.plugin.Before;
import com.luckyframework.httpclient.proxy.plugin.ExecuteMeta;
import io.github.lucklike.httpclient.plugin.HttpPlugin;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author fukang
 * @version 1.0.0
 * @date 2025/2/16 12:55
 */
@HttpPlugin
@Component
public class FieldPlugin {

    @Before("true")
    public void before(ExecuteMeta meta) {
        Method method = meta.getMethod();
        System.out.println("Running " + method.getName() + ", Args:" + Arrays.toString(meta.getArgs()));
    }
}
