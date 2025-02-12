package io.github.lucklike.luckyclient.api.ollama.req;

import lombok.Data;

@Data
public class ModelPullRequest {

    /**
     * 要拉取的模型名称
     */
    private String name;

    /**
     * （可选）：允许对库进行不安全连接。建议仅在开发期间，从自己的库中拉取时使用此选项。
     */
    private Boolean insecure;

    /**
     * （可选）：如果为 false，响应将作为单个响应对象返回，而不是对象流。
     */
    private Boolean stream;
}
