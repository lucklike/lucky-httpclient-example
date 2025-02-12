package io.github.lucklike.luckyclient.api.ollama.resp;

import lombok.Data;

@Data
public class OllamaBaseResponse {

    /**
     * 模型名称
     */
    private String model;

    /**
     * 时间
     */
    private String created_at;

    /**
     * 是否已经结束
     */
    private Boolean done;

    /**
     * 停止的原因
     */
    private String done_reason;

    /**
     * 生成响应所花费的时间（纳秒）
     */
    private Long total_duration;

    /**
     * 加载模型所花费的时间（纳秒）
     */
    private Long load_duration;

    /**
     * 提示中的标记数量
     */
    private Integer prompt_eval_count;

    /**
     * 评估提示所花费的时间（纳秒）
     */
    private Long prompt_eval_duration;

    /**
     * 响应中的标记数量
     */
    private Integer eval_count;

    /**
     * 生成响应所花费的时间（纳秒）
     */
    private Long eval_duration;

}
