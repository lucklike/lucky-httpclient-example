package io.github.lucklike.luckyclient.api.websocket.kdxf;

import com.luckyframework.common.StringUtils;
import io.github.lucklike.luckyclient.api.cairh.BizException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Slf4j
@Component("kdxfConfig")
@ConfigurationProperties(prefix = "kdxf.voice.asr")
public class Configuration {
    private String httpUrl;
    private String wsUrl;
    private String appId;
    private String appKey;
    private String appSecret;

    public String getHttpUrl() {
        check("asrHttpUrl", httpUrl);
        return httpUrl;
    }

    public String getAppId() {
        check("appId", appId);
        return appId;
    }

    public String getAppKey() {
        check("appKey", appKey);
        return appKey;
    }

    public String getAppSecret() {
        check("appSecret", appSecret);
        return appSecret;
    }

    public String getWsUrl() {
        check("asrWsUrl", wsUrl);
        return wsUrl;
    }

    private void check(String name, String value) {
        if (!StringUtils.hasText(value)) {
            throw new BizException("缺少必要的配置：cpe.comp.voice-asr.kdxf.{}", name).printException(log);
        }
    }
}
