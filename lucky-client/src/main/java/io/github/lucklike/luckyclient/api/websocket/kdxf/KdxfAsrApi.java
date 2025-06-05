package io.github.lucklike.luckyclient.api.websocket.kdxf;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Slf4j
@Component
public class KdxfAsrApi {

    @Resource
    private KdxfTokenManager kdxfTokenManager;
    @Resource
    private Configuration config;

    public String asr(byte[] wavData) throws Exception {

        // 调用HTTP接口获取AccessToken
        String accessToken = kdxfTokenManager.getToken().getAccessToken();

        // 调用Ws接口解析音频文件
        WsApi wsApi = new WsApi(config.createWsUrl(accessToken));
        return wsApi.parseAudioFile(wavData);

    }
}

