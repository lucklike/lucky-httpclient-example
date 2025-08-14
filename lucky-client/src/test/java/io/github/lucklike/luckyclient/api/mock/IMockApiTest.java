package io.github.lucklike.luckyclient.api.mock;

import com.luckyframework.common.ConfigurationMap;
import com.luckyframework.spel.LazyValue;
import io.github.lucklike.httpclient.ApplicationContextUtils;
import io.github.lucklike.httpclient.injection.Bind;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
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
    }

    @Test
    void m307() {
        String s = api.m307();
        System.out.println(s);
    }
}