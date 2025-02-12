package io.github.lucklike.luckyclient.api.ollama.req;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 回答补全接口请求
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class GenerateRequest extends OllamaStreamRequest {

    /**
     * 要生成响应的提示
     */
    private String prompt;

    /**
     * 模型响应后的文本
     */
    private String suffix;

    /**
     * （可选）一个base64编码的图像列表（用于多模态模型，如 llava ）
     */
    private String images;

    /**
     * 返回响应的格式。目前唯一接受的值是 json
     */
    private String format;

    /**
     * 其他模型参数，如 temperature、seed 等
     */
    private String options;

    /**
     * 系统消息
     */
    private String system;

    /**
     * 要使用的提示模板
     */
    private String template;

    /**
     * 从先前对 /generate 的请求中返回的上下文参数，可以用于保持简短的对话记忆
     */
    private Integer[] context;

    /**
     * 如果设置为 true ，将不会对提示进行任何格式化。如果您在请求API时指定了完整的模板提示，可以选择使用 raw 参数
     */
    private Boolean raw;

    /**
     * 控制模型在请求后保留在内存中的时间（默认：5m）
     */
    private String keep_alive;
}
