package io.github.lucklike.luckyclient.api.mock;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;

@SpringBootTest
class MockDemoApiTest {

    @Resource
    private MockDemoApi api;

    @Test
    void baidu() throws ExecutionException, InterruptedException {
        // 执行真正的请求
        String baiduIndex = api.baidu2("iii").get();
        // Mock
        String mockBaiduIndex = api.baidu("mock");
    }

}