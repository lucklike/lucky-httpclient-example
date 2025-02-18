package io.github.lucklike.luckyclient.api.plugin;

import com.luckyframework.common.Console;
import com.luckyframework.common.UnitUtils;
import com.luckyframework.httpclient.proxy.logging.FontUtil;
import com.luckyframework.httpclient.proxy.plugin.Around;
import com.luckyframework.httpclient.proxy.plugin.ExecuteMeta;
import com.luckyframework.httpclient.proxy.plugin.ProxyDecorator;
import io.github.lucklike.httpclient.plugin.HttpPlugin;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author fukang
 * @version 1.0.0
 * @date 2025/2/16 12:55
 */
@HttpPlugin
@Component
public class FieldPlugin {

    @Around("true")
    public Object around(ProxyDecorator decorator) throws Throwable {
        ExecuteMeta meta = decorator.getMeta();
        long startTime = System.currentTimeMillis();
        Object result = decorator.proceed();
        Console.println(
                "{} method takes {} time to run. args:{}",
                FontUtil.getGreenUnderline(meta.getMetaContext().getSimpleSignature()),
                FontUtil.getMulberryStr(UnitUtils.millisToTime((System.currentTimeMillis() - startTime))),
                FontUtil.getBackYellowStr(Arrays.toString(meta.getArgs())));
        return result;
    }
}
