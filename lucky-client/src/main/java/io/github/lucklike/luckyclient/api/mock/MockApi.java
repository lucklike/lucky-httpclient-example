package io.github.lucklike.luckyclient.api.mock;

import com.luckyframework.httpclient.proxy.context.MethodContext;
import com.luckyframework.httpclient.proxy.mock.MockResponse;
import com.luckyframework.reflect.Param;
import io.github.lucklike.httpclient.configapi.ResourceHttpClient;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;

/**
 * @author fukang
 * @version 1.0.0
 * @date 2024/8/17 05:25
 */
@ResourceHttpClient("classpath:api/mock/MockApi.yml")
public abstract class MockApi {


    @Value("${lucky-server.http}")
    private String apiKey;

    public abstract String baidu(String keyWord);

    public String getBaidu() {
        System.out.println("getBaidu");
        return baidu("Can You Feel My World");
    }

    public String getApiKey() {
        return apiKey;
    }

    public static MockResponse baiduMock(@Param("#{a}") String javaVersion, MethodContext context) {
        System.out.println("-----------------------Mock--------------------------");
        return MockResponse.create()
                .status(200)
                .header("Content-Type", "application/json; charset=utf-8")
                .header("Service", "Nginx-1.3")
                .body("百度一下，你就知道！" + context.getSignature());
    }
}
