package io.github.lucklike.luckyclient.api.ollama.req;

import lombok.Data;

@Data
public class CopyRequest {

    /**
     * 原模型
     */
    private String source;

    /**
     * 复制后的模型名
     */
    private String destination;

}
