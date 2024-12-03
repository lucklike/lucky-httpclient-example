package io.github.lucklike.luckyclient.api.cairh.config;

import lombok.Data;

@Data
public class CrhOpenApiProperties {

    /**
     * URL
     */
    private String url;

    /**
     * appId
     */
    private String appId;

    /**
     * secretKey
     */
    private String secretKey;

    /**
     * 连接超时时间，默认60秒
     */
    private Integer connectTimeout = 60000;
}
