package io.github.lucklike.luckyclient.api.server;

import com.luckyframework.io.MultipartFile;
import io.github.lucklike.luckyclient.api.server.ann.HelloWorldApi;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author fukang
 * @version 1.0.0
 * @date 2024/7/7 03:36
 */
@SpringBootTest
class HelloWorldApiTest {

    @Resource
    private HelloWorldApi api;

    @Test
    void hello() {
        System.out.println(api.hello());
    }

    @Test
    void exception() {
        System.out.println(api.exception());
    }

    @Test
    void baidu() {
        System.out.println(api.baidu());
    }

    @Test
    void getFile() {
        MultipartFile file = api.getFile();
        System.out.println(file);
    }
}