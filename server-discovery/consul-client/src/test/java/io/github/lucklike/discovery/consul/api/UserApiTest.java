package io.github.lucklike.discovery.consul.api;

import com.luckyframework.common.RunnableActuator;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserApiTest {

    @Resource
    private UserApi userApi;

    @Test
    void getUser() {
        RunnableActuator.repeat(10, () -> {
            System.out.println(userApi.getUser());
        });

    }
}