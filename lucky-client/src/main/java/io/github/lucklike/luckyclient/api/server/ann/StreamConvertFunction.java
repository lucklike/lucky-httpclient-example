package io.github.lucklike.luckyclient.api.server.ann;

import com.luckyframework.httpclient.proxy.spel.hook.Lifecycle;
import com.luckyframework.httpclient.proxy.spel.hook.callback.Callback;
import com.luckyframework.io.RepeatableReadStreamUtil;
import com.luckyframework.reflect.Param;

import java.io.IOException;
import java.io.InputStream;

public class StreamConvertFunction {

    @Callback(lifecycle = Lifecycle.METHOD)
    public static void autoStreamConvert(@Param("#{$args$}") Object[] args) throws IOException {
        for (int i = 0; i < args.length; i++) {
            Object arg = args[i];
            if (arg instanceof InputStream) {
                args[i] = RepeatableReadStreamUtil.useFileStore((InputStream) arg);
            }
        }
    }
}
