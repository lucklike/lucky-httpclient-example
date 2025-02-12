package io.github.lucklike.luckyclient.api.ollama.listener;


import com.luckyframework.common.Color;
import com.luckyframework.common.Console;
import com.luckyframework.httpclient.generalapi.DelayedOutput;
import com.luckyframework.httpclient.proxy.sse.OnMessage;
import com.luckyframework.httpclient.proxy.sse.ndjson.AnnotationNdJsonEventListener;
import io.github.lucklike.luckyclient.api.ollama.resp.GenerateResponse;

public class OllamaGenerateEventListener extends AnnotationNdJsonEventListener<GenerateResponse> {

    private final ThreadLocal<GenerateResponse> lastResp = new ThreadLocal<>();

    public Integer[] getContext() {
        GenerateResponse response = lastResp.get();
        return response == null ? null : response.getContext();
    }

    public void removeContext() {
        lastResp.remove();
    }

    @OnMessage("#{$data$.done}")
    public void clear(GenerateResponse response) {
        lastResp.set(response);
        Console.printlnMulberry("\n");
        DelayedOutput.clearOutputLength();
    }

    @OnMessage
    public void output(GenerateResponse response) {
        DelayedOutput.output(response.getResponse(), Color.CYAN, 70, 10);
    }
}
