package io.github.lucklike.luckyclient.api.ollama.resp;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ModelInfo {

    @JsonProperty("general.architecture")
    private String general$architecture;

    @JsonProperty("general.basename")
    private String general$basename;

    @JsonProperty("general.file_type")
    private Integer general$file_type;

    @JsonProperty("general.parameter_count")
    private Long general$parameter_count;

    @JsonProperty("general.quantization_version")
    private Integer general$quantization_version;

    @JsonProperty("general.size_label")
    private String general$size_label;

    @JsonProperty("general.type")
    private String general$type;

    @JsonProperty("qwen2.attention.head_count")
    private Integer qwen2$attention$head_count;

    @JsonProperty("qwen2.attention.head_count_kv")
    private Integer qwen2$attention$head_count_kv;

    @JsonProperty("qwen2.attention.layer_norm_rms_epsilon")
    private Double qwen2$attention$layer_norm_rms_epsilon;

    @JsonProperty("qwen2.block_count")
    private Integer qwen2$block_count;

    @JsonProperty("qwen2.context_length")
    private Long qwen2$context_length;

    @JsonProperty("qwen2.embedding_length")
    private Long qwen2$embedding_length;

    @JsonProperty("qwen2.feed_forward_length")
    private Long qwen2$feed_forward_length;

    @JsonProperty("qwen2.rope.freq_base")
    private Long qwen2$rope$freq_base;

    @JsonProperty("tokenizer.ggml.add_bos_token")
    private Boolean tokenizer$ggml$add_bos_token;

    @JsonProperty("tokenizer.ggml.add_eos_token")
    private Boolean tokenizer$ggml$add_eos_token;

    @JsonProperty("tokenizer.ggml.bos_token_id")
    private Long tokenizer$ggml$bos_token_id;

    @JsonProperty("tokenizer.ggml.eos_token_id")
    private Long tokenizer$ggml$eos_token_id;

    @JsonProperty("tokenizer.ggml.merges")
    private String[] tokenizer$ggml$merges;

    @JsonProperty("tokenizer.ggml.model")
    private String tokenizer$ggml$model;

    @JsonProperty("tokenizer.ggml.padding_token_id")
    private Long tokenizer$ggml$padding_token_id;

    @JsonProperty("tokenizer.ggml.pre")
    private String tokenizer$ggml$pre;

    @JsonProperty("tokenizer.ggml.token_type")
    private Integer[] tokenizer$ggml$token_type;

    @JsonProperty("tokenizer.ggml.tokens")
    private String[] tokenizer$ggml$tokens;

}
