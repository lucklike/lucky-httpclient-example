package io.github.lucklike.luckyclient.api.ollama.req;

import lombok.Data;

@Data
public class OllamaBaseRequest {

    /**
     * 模型名称
     */
    private String model;
}
