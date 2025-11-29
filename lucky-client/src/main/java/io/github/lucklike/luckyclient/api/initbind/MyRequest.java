package io.github.lucklike.luckyclient.api.initbind;

import lombok.Data;

/**
 * 请求类
 * @author fukang
 * @version 1.0.0
 * @date 2025/9/28 19:36
 */
@Data
public class MyRequest {
    private String appId;
    private String appKey;
    private String appSecret;

    private String funCode;
    private String requestId;
}
