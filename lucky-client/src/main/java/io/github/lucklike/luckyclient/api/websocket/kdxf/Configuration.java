package io.github.lucklike.luckyclient.api.websocket.kdxf;

import cn.hutool.core.lang.id.NanoId;
import com.luckyframework.common.StringUtils;
import io.github.lucklike.luckyclient.api.cairh.BizException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.net.URI;

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


    public URI createWsUrl(String accessToken) {
        String sessionId = NanoId.randomNanoId(16);
        String appId = "CRH" + NanoId.randomNanoId(4);
        String bizName = "WebSocket";
        String lan = "chin";
        int sr = 16000;
        int bps = 16;

        String urlTemp = "speech/service/asr/%s" +
                "?appId=%s" +
                "&bizId=%s" +
                "&bizName=%s" +
                "&lan=%s" +
                "&sr=%s" +
                "&bps=%s" +
                "&fs=4096" +
                "&zaiauth=%s";
        String url = String.format(urlTemp, sessionId, appId, sessionId, bizName, lan, sr, bps, accessToken);
        url = StringUtils.joinUrlPath(wsUrl, url);

        System.out.println("WsUrl: " + url);
        return URI.create(url);
    }
}
