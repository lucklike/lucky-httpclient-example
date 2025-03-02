package io.github.lucklike.luckyclient.api.mock;

import com.luckyframework.httpclient.proxy.sse.Event;
import com.luckyframework.httpclient.proxy.sse.EventListener;
import com.luckyframework.io.MultipartFile;
import com.luckyframework.reflect.MethodUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author fukang
 * @version 1.0.0
 * @date 2025/3/2 09:02
 */
@SpringBootTest
class MockApiTest {

    @Resource
    private MockApi mockApi;

    @Test
    void html() {
        String html = mockApi.html();
        System.out.println(html);
    }

    @Test
    void _404() {
        String txt = mockApi._404();
        System.out.println(txt);
    }

    @Test
    void _500() {
        String txt = mockApi._500();
        System.out.println(txt);
    }

    @Test
    void download() throws IOException {
        MultipartFile png = mockApi.download();
        System.out.printf("Image: [%s]%s\n", png.getSize(), png.getOriginalFileName());
    }

    @Test
    void mockResp() {
        String resp = mockApi.mockResp("Param-1", 222);
        System.out.println(resp);
    }


    @Test
    void mockFunc() {
        Map<String, Object> resp = mockApi.mockFunc("Param-1", "Param-2");
        System.out.println(resp);
    }

    @Test
    void appoint() throws IOException {
        MultipartFile file = mockApi.appoint("240802150543730793512287.mp3");
        System.out.printf("File: [%s]%s\n", file.getSize(), file.getOriginalFileName());
    }

    @Test
    void sse() {
        mockApi.sse2(new EventListener() {
            @Override
            public void onText(Event<String> event) throws Exception {
                System.out.printf("收到服务器消息：%s\n", event.getMessage());
            }
        });
    }

}