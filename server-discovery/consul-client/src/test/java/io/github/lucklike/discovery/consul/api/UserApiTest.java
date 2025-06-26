package io.github.lucklike.discovery.consul.api;

import com.luckyframework.common.RunnableActuator;
import io.github.lucklike.common.api.UserApi;
import io.github.lucklike.httpclient.injection.HttpReference;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserApiTest {

    @HttpReference
    private UserApi userApi;

    @Test
    void getUser() {
        RunnableActuator.repeat(10, i -> {
            System.out.println(userApi.getUser(i + ""));
        });

    }
}