package io.github.lucklike.luckyclient.api.server.nacos;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author fukang
 * @version 1.0.0
 * @date 2025/2/18 23:13
 */
@SpringBootTest
class LuckyNacosServerApiTest {

    @Resource
    LuckyNacosServerApi luckyNacosServerApi;

    @Test
    void hello() {
        for (int i = 0; i < 100; i++) {
            System.out.println(luckyNacosServerApi.hello());
            System.out.println(luckyNacosServerApi.getUser(i+1));
        }
    }
}