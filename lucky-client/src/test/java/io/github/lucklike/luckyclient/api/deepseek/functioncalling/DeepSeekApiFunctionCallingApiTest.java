package io.github.lucklike.luckyclient.api.deepseek.functioncalling;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DeepSeekApiFunctionCallingApiTest {

    @Resource
    private DeepSeekApiFunctionCallingApi api;

    @Test
    void completions() {
        String r = api.completions("帮我查询以下今天湖南的油价");
        System.out.println(r);
    }
}