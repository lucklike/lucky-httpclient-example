package io.github.lucklike.luckyclient.api.ollama.req;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class OllamaStreamRequest extends OllamaBaseRequest {

    /**
     * 如果设置为 false ，响应将作为单个响应对象返回，而不是一系列对象流
     */
    private Boolean stream;
}
