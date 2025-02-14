package io.github.lucklike.luckyclient.api.cairh.ttd;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "ttd")
public class TTDConfig {

    private String url;
    private String key;
    private String iv;
    private String appId;
    private Integer connectionTimeout;
    private Integer readTimeout;

}
