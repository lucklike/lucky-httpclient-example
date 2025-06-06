package io.github.lucklike.luckyclient.api.websocket;

import io.github.lucklike.luckyclient.api.websocket.kdxf.WsApi;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.FileCopyUtils;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;

@SpringBootTest
class WsApiTest {
    @Resource
    private WsApi wsApi;


    @Test
    void wsTest() throws IOException {
        String asr = wsApi.parseAudioFile(FileCopyUtils.copyToByteArray(new File("D:\\test\\5A301B37FAA42122CD00E381D2D7BD66.wav")));
        System.out.println(asr);

        String asr2 = wsApi.parseAudioFile(FileCopyUtils.copyToByteArray(new File("D:\\Download\\Arc\\240802150543730793512287.wav")));
        System.out.println(asr2);
    }

}