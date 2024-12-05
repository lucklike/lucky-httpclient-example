package io.github.lucklike.luckyclient.api.mock;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MockDemoApiTest {

    @Resource
    private MockDemoApi api;

    @Test
    void baidu() {
        // 执行真正的请求
        String baiduIndex = api.baidu("iii");
        // Mock
        String mockBaiduIndex = api.baidu("mock");
    }
}