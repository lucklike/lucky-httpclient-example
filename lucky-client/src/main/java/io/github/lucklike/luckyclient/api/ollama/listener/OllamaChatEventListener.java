package io.github.lucklike.luckyclient.api.ollama.listener;


import com.luckyframework.common.Color;
import com.luckyframework.common.Console;
import com.luckyframework.httpclient.generalapi.DelayedOutput;
import com.luckyframework.httpclient.proxy.sse.OnMessage;
import com.luckyframework.httpclient.proxy.sse.ndjson.AnnotationNdJsonEventListener;
import io.github.lucklike.luckyclient.api.ollama.resp.ChatResponse;

public class OllamaChatEventListener extends AnnotationNdJsonEventListener<ChatResponse> {

    @OnMessage("#{$data$.done}")
    public void clear() {
        Console.printlnMulberry("\n");
        DelayedOutput.clearOutputLength();
    }

    @OnMessage
    public void output(ChatResponse response) {
        DelayedOutput.output(response.getMessage().getContent(), Color.CYAN, 70, 10);
    }
}
