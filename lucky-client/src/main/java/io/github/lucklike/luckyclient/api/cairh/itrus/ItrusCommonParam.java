package io.github.lucklike.luckyclient.api.cairh.itrus;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 公共参数
 * @author fukang
 * @version 1.0.0
 * @date 2025/1/9 00:54
 */
@Data
@Component
@ConfigurationProperties(prefix = "cairh.itrus")
public class ItrusCommonParam {
    private String url;
    private String appId;
    private String appSecret;
    private String serviceCode;
    private String companyUUID;
}
