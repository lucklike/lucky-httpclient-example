package io.github.lucklike.luckyclient.api.ollama.resp;

import lombok.Data;

@Data
public class ModelList {

    /**
     * 模型集合
     */
    private Model[] models;
}
