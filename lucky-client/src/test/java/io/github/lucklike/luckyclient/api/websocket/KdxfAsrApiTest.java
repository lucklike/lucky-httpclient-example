package io.github.lucklike.luckyclient.api.websocket;

import io.github.lucklike.luckyclient.api.websocket.kdxf.KdxfAsrApi;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.FileCopyUtils;

import javax.annotation.Resource;
import java.io.File;

@SpringBootTest
class KdxfAsrApiTest {

    @Resource
    private KdxfAsrApi kdxfAsrApi;


    @Test
    void asrTest() throws Exception {
        String asr = kdxfAsrApi.asr(FileCopyUtils.copyToByteArray(new File("D:\\Download\\Arc\\240802150543730793512287.wav")));
        System.out.println(asr);

        String asr2 = kdxfAsrApi.asr(FileCopyUtils.copyToByteArray(new File("D:\\test\\5A301B37FAA42122CD00E381D2D7BD66.wav")));
        System.out.println(asr2);
    }
}