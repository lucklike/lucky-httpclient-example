package io.github.lucklike.luckyclient.api.baiduai;

import com.luckyframework.common.Console;
import com.luckyframework.httpclient.proxy.sse.AnnotationEventListener;
import com.luckyframework.httpclient.proxy.sse.Message;
import com.luckyframework.httpclient.proxy.sse.OnMessage;
import com.luckyframework.reflect.Param;
import io.github.lucklike.luckyclient.api.util.DelayedOutput;

public class BaiduAISseEventListener extends AnnotationEventListener {

    @OnMessage("#{$jdata$.is_end}")
    public void clear() {
        Console.printlnMulberry("\n");
        DelayedOutput.clearOutputLength();
    }

    @OnMessage
    public void printContext(@Param("#{$jdata$.result}") String context) {
        DelayedOutput.output(context, 70, 20);
    }
}
