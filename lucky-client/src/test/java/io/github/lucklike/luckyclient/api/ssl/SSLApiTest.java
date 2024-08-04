package io.github.lucklike.luckyclient.api.ssl;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author fukang
 * @version 1.0.0
 * @date 2024/8/4 01:38
 */
@SpringBootTest
public class SSLApiTest {

    @Resource
    private SSLApi sslApi;

    @Test
    void test() {
        System.out.println(sslApi.test());
    }
}
