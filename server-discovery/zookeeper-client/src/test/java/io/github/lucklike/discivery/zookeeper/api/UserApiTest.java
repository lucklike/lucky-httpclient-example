package io.github.lucklike.discivery.zookeeper.api;

import com.luckyframework.common.RunnableActuator;
import io.github.lucklike.entity.request.User;
import io.github.lucklike.entity.response.Result;
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
            Result<User> user = userApi.getUser();
            System.out.println(user);
        });
    }
}