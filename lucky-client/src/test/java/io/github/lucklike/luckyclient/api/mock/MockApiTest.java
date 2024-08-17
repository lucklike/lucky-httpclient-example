package io.github.lucklike.luckyclient.api.mock;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author fukang
 * @version 1.0.0
 * @date 2024/8/17 05:27
 */
@SpringBootTest
class MockApiTest {

    @Resource
    MockApi mockApi;

    @Test
    void baidu() {
        System.out.println(mockApi.baidu());
    }
}