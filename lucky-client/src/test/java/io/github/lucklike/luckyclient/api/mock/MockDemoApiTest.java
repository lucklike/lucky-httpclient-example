package io.github.lucklike.luckyclient.api.mock;

import com.luckyframework.httpclient.proxy.spel.ParameterInfo;
import com.luckyframework.reflect.AnnotationUtils;
import io.github.lucklike.httpclient.parameter.ParameterInstanceFactory;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.lang.NonNull;

import javax.annotation.Resource;

import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;

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