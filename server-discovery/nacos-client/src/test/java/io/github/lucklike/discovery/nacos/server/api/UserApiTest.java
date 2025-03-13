package io.github.lucklike.discovery.nacos.server.api;

import com.luckyframework.common.RunnableActuator;
import io.github.lucklike.common.api.UserApi;
import io.github.lucklike.entity.request.User;
import io.github.lucklike.entity.response.Result;
import io.github.lucklike.httpclient.annotation.HttpReference;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author fukang
 * @version 1.0.0
 * @date 2025/3/12 23:08
 */
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