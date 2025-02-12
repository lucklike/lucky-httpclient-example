package io.github.lucklike.luckyclient.api.ollama;

import com.luckyframework.httpclient.generalapi.AutoVerifyHttpStatus;
import com.luckyframework.httpclient.proxy.annotations.PrintLogProhibition;
import com.luckyframework.httpclient.proxy.context.MethodContext;
import com.luckyframework.httpclient.proxy.spel.SpELImport;
import com.luckyframework.httpclient.proxy.spel.hook.Lifecycle;
import com.luckyframework.httpclient.proxy.spel.hook.callback.Callback;
import com.luckyframework.httpclient.proxy.sse.SseResultConvert;
import io.github.lucklike.httpclient.annotation.HttpClient;
import io.github.lucklike.luckyclient.api.ollama.req.OllamaBaseRequest;
import io.github.lucklike.luckyclient.api.ollama.req.OllamaStreamRequest;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@AutoVerifyHttpStatus
//@PrintLogProhibition
@SpELImport(OllamaClient.OllamaFunctions.class)
@HttpClient("#{@ollamaConfigProperties.url}")
public @interface OllamaClient {


    /**
     * Ollama API相关的函数和回调
     */
    class OllamaFunctions {

        /**
         * 用于添加模型参数的回调函数
         *
         * @param context 方法上下文对象
         * @param config  Ollama配置对象
         */
        @Callback(lifecycle = Lifecycle.REQUEST_INIT)
        public static void addModel(MethodContext context, OllamaConfigProperties config) {
            boolean isSse = context.isAnnotated(SseResultConvert.class);
            for (Object argument : context.getArguments()) {
                if (argument instanceof OllamaBaseRequest) {
                    ((OllamaBaseRequest) argument).setModel(config.getModel());
                }
                if (!isSse && argument instanceof OllamaStreamRequest) {
                    ((OllamaStreamRequest) argument).setStream(false);
                }
            }
        }
    }
}
