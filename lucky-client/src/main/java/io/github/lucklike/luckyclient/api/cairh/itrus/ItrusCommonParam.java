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

    /**
     * 接口地址
     */
    private String url;

    /**
     * AppID
     */
    private String appId;

    /**
     * AppSecret
     */
    private String appSecret;

    /**
     * 主企业ID
     * CompanyUUID
     */
    private String companyUUID;

    /**
     * 主企业签章ID
     */
    private Long companySealId;
}
