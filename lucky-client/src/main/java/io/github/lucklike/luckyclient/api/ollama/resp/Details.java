package io.github.lucklike.luckyclient.api.ollama.resp;

import lombok.Data;

@Data
public class Details {

    /**
     * 父模型
     */
    private String parent_model;

    /**
     * 格式
     */
    private String format;

    /**
     * 家族
     */
    private String family;

    /**
     * 家族集合
     */
    private String[] families;

    /**
     * 参数大小
     */
    private String parameter_size;

    /**
     * 量子化水平
     */
    private String quantization_level;
}
