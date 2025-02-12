package io.github.lucklike.luckyclient.api.ollama.req;

import lombok.Data;

@Data
public class ModelRequest {

    /**
     * 要显示的模型名称
     */
    private String name;

    /**
     * （可选）：如果设置为 true，则返回详细响应字段的完整数据
     */
    private Boolean verbose;

}
