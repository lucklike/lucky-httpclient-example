package io.github.lucklike.luckyclient.api.ifexp;

import io.github.lucklike.entity.request.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author fukang
 * @version 1.0.0
 * @date 2024/9/5 02:59
 */
@SpringBootTest
class AutoAddTokenApiTest {

    @Resource
    private AutoAddTokenApi autoAddTokenApi;

    @Test
    void test() {

        // 此时还没有登录，会自动调用登录接口去获取token
        autoAddTokenApi.getUser();

        // 已经登录，token已经保存，会自动带上
        User user = new User();
        user.setId(1234);
        autoAddTokenApi.addUser(user);

    }
}