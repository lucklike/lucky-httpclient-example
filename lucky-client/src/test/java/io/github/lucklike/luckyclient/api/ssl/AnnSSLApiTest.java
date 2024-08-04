package io.github.lucklike.luckyclient.api.ssl;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author fukang
 * @version 1.0.0
 * @date 2024/8/3 18:16
 */
@SpringBootTest
public class AnnSSLApiTest {

    @Resource
    private AnnSSLApi api;

    @Test
    void test() {
        System.out.println(api.test());
    }
}
