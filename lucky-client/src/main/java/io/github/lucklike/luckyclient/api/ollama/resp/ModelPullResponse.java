package io.github.lucklike.luckyclient.api.ollama.resp;

import lombok.Data;

@Data
public class ModelPullResponse {

    /**
     * 下载状态
     */
    private String status;

    /**
     * digest
     */
    private String digest;

    /**
     * 总大小
     */
    private Long total;

    /**
     * 已完成的
     */
    private Long completed;
}
