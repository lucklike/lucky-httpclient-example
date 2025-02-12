package io.github.lucklike.luckyclient.api.ollama.resp;

import lombok.Data;

@Data
public class ModelDetails {

    /**
     * 许可证
     */
    private String license;

    /**
     * 模型文件
     */
    private String modelfile;

    /**
     * 参数
     */
    private String parameters;

    /**
     * 模版信息
     */
    private String template;

    /**
     * 模型细节
     */
    private Details details;

    /**
     * 最近修改时间
     */
    private String modified_at;

    /**
     * 模型信息
     */
    private ModelInfo model_info;
}
