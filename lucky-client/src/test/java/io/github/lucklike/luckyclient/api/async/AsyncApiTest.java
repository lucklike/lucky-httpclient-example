package io.github.lucklike.luckyclient.api.async;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class AsyncApiTest {

    @Resource
    private AsyncApi api;

    @Test
    void name() throws InterruptedException {
        api.get(rc -> System.out.println(rc.getResult()));
        Thread.sleep(2000);
    }

}
