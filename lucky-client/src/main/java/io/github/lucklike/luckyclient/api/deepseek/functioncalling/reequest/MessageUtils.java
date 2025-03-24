package io.github.lucklike.luckyclient.api.deepseek.functioncalling.reequest;

import com.luckyframework.common.ConfigurationMap;
import lombok.Data;

import java.util.List;

@Data
public class MessageUtils {

    public static ConfigurationMap createUserMessage(String content) {
        ConfigurationMap message = new ConfigurationMap();
        message.put("role", "user");
        message.put("content", content);
        return message;
    }

    public static ConfigurationMap createToolMessage(String callId, String content) {
        ConfigurationMap message = new ConfigurationMap();
        message.put("role", "tool");
        message.put("tool_call_id", callId);
        message.put("content", content);
        return message;
    }

    public static void append(List<ConfigurationMap> messages, ConfigurationMap message) {
        messages.add(message);
    }
}
