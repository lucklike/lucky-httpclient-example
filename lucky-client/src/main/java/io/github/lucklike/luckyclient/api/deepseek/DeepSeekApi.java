package io.github.lucklike.luckyclient.api.deepseek;

import com.luckyframework.common.Console;
import com.luckyframework.httpclient.generalapi.AutoVerifyHttpStatus;
import com.luckyframework.httpclient.proxy.annotations.HttpExec;
import com.luckyframework.httpclient.proxy.annotations.Post;
import com.luckyframework.httpclient.proxy.annotations.PropertiesJson;
import com.luckyframework.httpclient.proxy.annotations.StaticHeader;
import com.luckyframework.httpclient.proxy.annotations.TextEventStream;
import com.luckyframework.httpclient.proxy.sse.OnMessage;
import com.luckyframework.httpclient.proxy.sse.Sse;
import com.luckyframework.httpclient.proxy.sse.standard.AnnotationStandardEventListener;
import com.luckyframework.reflect.Param;
import io.github.lucklike.httpclient.discovery.HttpClient;
import io.github.lucklike.luckyclient.api.util.DelayedOutput;
import reactor.core.publisher.Flux;

/**
 * DeepSeek API
 *
 * @author fukang
 * @version 1.0.0
 * @date 2025/2/6 10:43
 */
@AutoVerifyHttpStatus
@HttpClient("${DeepSeek.url}")
public interface DeepSeekApi {

    @PropertiesJson({
        "stream=#{true}",
        "model=deepseek-chat",
        "messages[0].role=system",
        "messages[0].content=You are a helpful assistant.",
        "messages[1].role=user",
        "messages[1].content=#{content}"
    })
    @Sse(listenerClass = DeepSeekEventListener.class)
    @StaticHeader("Authorization: Bearer ${DeepSeek.apiKey}")
    @Post("/chat/completions")
    void completions(String content);

    @PropertiesJson({
            "stream=#{true}",
            "model=deepseek-chat",
            "messages[0].role=system",
            "messages[0].content=You are a helpful assistant.",
            "messages[1].role=user",
            "messages[1].content=#{content}"
    })
    @HttpExec.http_client5
    @TextEventStream
    @StaticHeader("Authorization: Bearer ${DeepSeek.apiKey}")
    @Post("/chat/completions")
    Flux<String> completionsFlux(String content);


    class DeepSeekEventListener extends AnnotationStandardEventListener {

        @OnMessage("#{$data$ eq ' [DONE]'}")
        public void end() {
            Console.printlnMulberry("\n");
            DelayedOutput.clearOutputLength();
        }

        @OnMessage
        public void onMessage(@Param("#{$jdata$.choices[0].delta.content}") String message) {
            DelayedOutput.output(message, 70, 20);
        }
    }
}
