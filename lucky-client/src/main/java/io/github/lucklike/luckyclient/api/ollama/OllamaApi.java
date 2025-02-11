package io.github.lucklike.luckyclient.api.ollama;

import com.luckyframework.httpclient.proxy.annotations.JsonParam;
import com.luckyframework.httpclient.proxy.annotations.Post;
import com.luckyframework.httpclient.proxy.annotations.PropertiesJson;
import com.luckyframework.httpclient.proxy.sse.Sse;

/**
 * Ollama API
 * Ollama 提供了强大的 REST API，
 * 使开发者能够方便地与大语言模型进行交互。通过 Ollama API，
 * 用户可以发送请求并接收模型生成的响应，应用于自然语言处理、文本生成等任务。
 * 本文将详细介绍生成补全、对话生成的基本操作，并对创建模型、复制模型、删除模型等常见操作也进行了说明。
 */
@OllamaClient
public interface OllamaApi {

    @Sse(listenerClass = OllamaEventListener.class)
    @PropertiesJson("model=#{@ollamaConfigProperties.model}")
    @Post("api/generate")
    void generate(@JsonParam String prompt);
}
