package io.github.lucklike.luckyclient.api.openai;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author fukang
 * @version 1.0.0
 * @date 2024/12/15 18:32
 */
@SpringBootTest
class ChatGPTApiTest {

    @Resource
    private ChatGPTApi api;

    @Test
    void send() {
        System.out.println(api.send("你好"));
    }
}