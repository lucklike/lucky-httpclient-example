package io.github.lucklike.luckyclient.api.ollama.resp;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = true)
public class GenerateResponse extends OllamaBaseResponse {

    /**
     * 响应内容
     */
    private String response;


    /**
     * 用于此响应的对话编码，可以在下一个请求中发送以保持对话记忆
     */
    private Integer[] context;

}
