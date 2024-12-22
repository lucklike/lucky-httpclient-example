package io.github.lucklike.server.ai.baidu;

import com.luckyframework.common.Console;
import com.luckyframework.httpclient.generalapi.DelayedOutput;
import com.luckyframework.httpclient.proxy.sse.AnnotationEventListener;
import com.luckyframework.httpclient.proxy.sse.Event;
import com.luckyframework.httpclient.proxy.sse.OnMessage;
import com.luckyframework.reflect.Param;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;


public class BaiduAISseEventListener extends AnnotationEventListener {

    private final SseEmitter emitter;

    public BaiduAISseEventListener(SseEmitter sseEmitter) {
        super();
        this.emitter = sseEmitter;
    }

    @OnMessage("#{!$jdata$.is_end}")
    public void printContext(@Param("#{$jdata$.result}") String context) throws IOException, InterruptedException {
        emitter.send(SseEmitter.event().data(context.replace("\n", "<br/>")));
    }

    @Override
    public void onError(Event<Throwable> event) {
        emitter.completeWithError(event.getMessage());
    }

    @Override
    public void onClose(Event<Void> event) {
        emitter.complete();
    }
}
