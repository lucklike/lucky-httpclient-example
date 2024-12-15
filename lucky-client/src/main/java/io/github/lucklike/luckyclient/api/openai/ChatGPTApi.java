package io.github.lucklike.luckyclient.api.openai;

import com.luckyframework.httpclient.proxy.annotations.Post;
import com.luckyframework.httpclient.proxy.annotations.PropertiesJson;
import com.luckyframework.httpclient.proxy.annotations.StaticHeader;
import io.github.lucklike.httpclient.annotation.HttpClient;

/**
 * @author fukang
 * @version 1.0.0
 * @date 2024/12/15 18:28
 */
@HttpClient("${ChatGPT.url}")
public interface ChatGPTApi {


    @PropertiesJson({
        "prompt=#{prompt}",
        "max_tokens=${ChatGPT.maxTokens}",
        "temperature=${ChatGPT.temperature}"
    })
    @Post
    @StaticHeader("Authorization: Bearer ${ChatGPT.apiKey}")
    String send(String prompt);
}
