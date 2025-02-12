package io.github.lucklike.luckyclient.api.ollama.resp;

import io.github.lucklike.luckyclient.api.ollama.req.Message;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ChatResponse extends OllamaBaseResponse {

    /**
     * 回复消息
     */
    private Message message;
}
