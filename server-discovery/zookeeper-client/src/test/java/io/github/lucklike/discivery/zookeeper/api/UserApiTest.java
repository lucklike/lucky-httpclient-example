package io.github.lucklike.discivery.zookeeper.api;

import com.luckyframework.common.RunnableActuator;
import io.github.lucklike.common.api.UserApi;
import io.github.lucklike.entity.request.User;
import io.github.lucklike.entity.response.Result;
import io.github.lucklike.httpclient.annotation.HttpReference;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserApiTest {

    @HttpReference
    private UserApi userApi;

    @Test
    void getUser() {
        RunnableActuator.repeat(10, i -> {
           Result<User> user = userApi.getUser(i+"");
            System.out.println(user);
        });
    }
}