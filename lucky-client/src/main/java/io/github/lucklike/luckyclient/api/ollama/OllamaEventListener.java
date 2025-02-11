package io.github.lucklike.luckyclient.api.ollama;

import com.luckyframework.httpclient.proxy.sse.AnnotationEventListener;
import com.luckyframework.httpclient.proxy.sse.Message;
import com.luckyframework.httpclient.proxy.sse.OnMessage;

public class OllamaEventListener extends AnnotationEventListener {

    @OnMessage
    public void printMsg(Message message) {
        System.out.println(message);
    }
}
