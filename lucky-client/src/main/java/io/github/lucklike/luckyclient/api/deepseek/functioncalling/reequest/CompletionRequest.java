package io.github.lucklike.luckyclient.api.deepseek.functioncalling.reequest;

import com.luckyframework.common.ConfigurationMap;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CompletionRequest {

    private String model;
    private List<ConfigurationMap> messages;
    private List<ConfigurationMap> tools;


    public void appendMessage(ConfigurationMap message) {
        if (messages == null) {
            messages = new ArrayList<>();
        }
        messages.add(message);
    }

    public void appendTool(ConfigurationMap tool) {
        if (tools == null) {
            tools = new ArrayList<>();
        }
        tools.add(tool);
    }

}
