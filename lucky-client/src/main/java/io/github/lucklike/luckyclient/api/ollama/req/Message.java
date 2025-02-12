package io.github.lucklike.luckyclient.api.ollama.req;

import lombok.Data;

@Data
public class Message {

    /**
     * 消息的角色，可以是 system、user、assistant 或 tool
     */
    private String role;

    /**
     * 消息的内容
     */
    private String content;

    /**
     * （可选）：要包含在消息中的图像列表（对于如 llava 之类的多模态模型）
     */
    private String images;

    /**
     * （可选）：模型想要使用的工具列表
     */
    private String tool_calls;
}
