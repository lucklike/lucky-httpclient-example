package io.github.lucklike.luckyclient.api.openai;

import com.luckyframework.httpclient.proxy.annotations.Post;
import com.luckyframework.httpclient.proxy.annotations.PropertiesJson;
import com.luckyframework.httpclient.proxy.annotations.StaticHeader;
import io.github.lucklike.httpclient.discovery.HttpClient;

/**
 * @author fukang
 * @version 1.0.0
 * @date 2024/12/15 18:28
 */
@HttpClient("${ChatGPT.url}")
@StaticHeader("Authorization: Bearer ${ChatGPT.apiKey}")
public interface ChatGPTApi {


    @PropertiesJson({
        "model=gpt-3.5-turbo-0125",
        "stream=#{true}",
        "messages[0].role=user",
        "messages[0].content=#{content}"
    })
    @Post("v1/chat/completions")
    String send(String content);
}
