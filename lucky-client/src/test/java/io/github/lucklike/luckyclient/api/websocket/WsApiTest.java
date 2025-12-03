package io.github.lucklike.luckyclient.api.websocket;

import com.luckyframework.httpclient.proxy.function.CommonFunctions;
import com.luckyframework.httpclient.proxy.function.ResourceFunctions;
import io.github.lucklike.luckyclient.api.websocket.kdxf.WsApi;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.FileCopyUtils;

import javax.annotation.Resource;
import java.io.IOException;

@SpringBootTest
class WsApiTest {
    @Resource
    private WsApi wsApi;


    @Test
    void wsTest() throws IOException {
        org.springframework.core.io.Resource resource = ResourceFunctions.resource("classpath:240802150543730793512287.wav");

        String asr = wsApi.parseAudioFile(FileCopyUtils.copyToByteArray(resource.getInputStream()));
        System.out.println(asr);

    }

}