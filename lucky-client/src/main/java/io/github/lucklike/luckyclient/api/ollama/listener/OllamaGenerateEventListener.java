package io.github.lucklike.luckyclient.api.ollama.listener;

import com.luckyframework.httpclient.core.meta.Response;
import com.luckyframework.httpclient.proxy.mock.NdJsonMock;
import com.luckyframework.httpclient.proxy.sse.Event;
import com.luckyframework.httpclient.proxy.sse.ndjson.NdJsonEventListener;
import io.github.lucklike.luckyclient.api.ollama.resp.GenerateResponse;

public class OllamaGenerateEventListener extends NdJsonEventListener<GenerateResponse> {


    @Override
    public void onOpen(Event<Response> event) throws Exception {
        System.out.println("连接已建立！");
    }

    @Override
    public void onMessage(Event<GenerateResponse> event) throws Exception {
        GenerateResponse message = event.getMessage();
        if (message.getDone()) {
            System.out.println("\nOllama API 响应结束");
        } else {
            System.out.print(message.getResponse());
        }
    }

    @Override
    public void onError(Event<Throwable> event) {
        System.out.println("发生异常: " + event.getMessage().getMessage());
    }


    @Override
    public void onClose(Event<Void> event) {
        System.out.println("连接已关闭");
    }

}
