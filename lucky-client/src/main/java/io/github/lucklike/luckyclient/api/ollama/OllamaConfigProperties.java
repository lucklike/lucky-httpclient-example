package io.github.lucklike.luckyclient.api.ollama;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "ollama")
public class OllamaConfigProperties {

    private String url;
    private String model;

}
