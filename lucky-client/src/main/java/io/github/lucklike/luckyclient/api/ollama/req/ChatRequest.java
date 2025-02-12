package io.github.lucklike.luckyclient.api.ollama.req;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 对话补全接口请求
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ChatRequest extends OllamaStreamRequest {

    /**
     * 聊天消息，这可以用于保持聊天记忆
     */
    private Message[] messages;

    /**
     * 模型支持使用的工具。需要将 stream 设置为 false
     */
    private Boolean tools;

    /**
     * 返回响应的格式。目前唯一接受的值是 json
     */
    private String format;

    /**
     * 其他模型参数，如 temperature、seed 等
     */
    private String options;

    /**
     * 控制模型在请求后保留在内存中的时间（默认：5m）
     */
    private String keep_alive;

}
