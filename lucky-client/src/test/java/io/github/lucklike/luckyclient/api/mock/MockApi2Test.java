package io.github.lucklike.luckyclient.api.mock;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author fukang
 * @version 1.0.0
 * @date 2024/8/17 05:27
 */
@SpringBootTest
class MockApi2Test {

    @Resource
    MockApi2 mockApi2;

    @Test
    void baidu() {
        mockApi2.baidu("Can You Feel My World");
        mockApi2.getBaidu();
        System.out.println(mockApi2.getApiKey());
//        System.out.println();
//        System.out.println(mockApi.baidu());
    }
}