package io.github.lucklike.luckyclient.api.ssl;

import io.github.lucklike.entity.request.User;
import io.github.lucklike.entity.response.Result;
import io.github.lucklike.luckyclient.api.ssl.configapi.SSLUserApi;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class SSLUserApiTest {

    @Resource
    private SSLUserApi api;

    @Test
    void postUserTest() {
        User user = new User();
        user.setId(1);
        user.setName("Jack的猫");
        user.setPassword("PA$$W0RD");
        user.setPhone("17363312985");
        user.setEmail("1814375626@qq.com");
        User userResult = api.postUser(user);

        System.out.println(userResult);
    }

    @Test
    void getUserTest() {
        Result<User> user = api.getUser();
        user = api.getUser();
        System.out.println(user);

        User postUser = api.postUser(user.getData());
        System.out.println(postUser);
    }
}
