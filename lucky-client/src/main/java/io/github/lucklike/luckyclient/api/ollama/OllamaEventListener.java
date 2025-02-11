package io.github.lucklike.luckyclient.api.ollama;


import com.luckyframework.common.ConfigurationMap;
import com.luckyframework.httpclient.generalapi.DelayedOutput;
import com.luckyframework.httpclient.proxy.sse.OnMessage;
import com.luckyframework.httpclient.proxy.sse.ndjson.AnnotationNdJsonEventListener;
import com.luckyframework.reflect.Param;

public class OllamaEventListener extends AnnotationNdJsonEventListener<ConfigurationMap> {

    @OnMessage("#{$data$.done}")
    public void clear() {
        DelayedOutput.clearOutputLength();
    }

    @OnMessage
    public void output(@Param("#{$data$.response}") String response) {
        DelayedOutput.output(response);
    }
}
