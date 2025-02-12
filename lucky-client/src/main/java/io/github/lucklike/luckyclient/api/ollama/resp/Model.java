package io.github.lucklike.luckyclient.api.ollama.resp;

import lombok.Data;

@Data
public class Model {

    /**
     * 名称
     */
    private String name;

    /**
     * 模型
     */
    private String model;

    /**
     * 模型文件创建时间
     */
    private String modified_at;

    /**
     * 获取时间
     */
    private String expires_at;

    /**
     * size_vram
     */
    private Long size_vram;

    /**
     * 大小
     */
    private Long size;

    /**
     * digest
     */
    private String digest;

    /**
     * 模型细节
     */
    private Details details;

}
