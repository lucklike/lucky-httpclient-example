package io.github.lucklike.luckyclient.api.mock;

import com.luckyframework.common.ConfigurationMap;
import com.luckyframework.httpclient.generalapi.HttpStatus;
import io.github.lucklike.httpclient.injection.Bind;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class IMockApiTest {

    @Resource
    private IMockApi api;

    @Bind("lucky.http-client.ssl.key-stores")
    private List<ConfigurationMap> list;

    @Test
    void m200() {
    }

    @Test
    void m404() {
        HttpStatus status = HttpStatus.getStatus(4444);
        System.out.println(status);
    }

    @Test
    void m307() {
        try {
            String s = api.m307();
            System.out.println(s);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}