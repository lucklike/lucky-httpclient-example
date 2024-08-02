package io.github.lucklike.luckyclient.api.server;

import io.github.lucklike.entity.request.User;
import io.github.lucklike.luckyclient.api.server.ann.AnnUserApi;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author fukang
 * @version 1.0.0
 * @date 2024/7/9 01:47
 */
@SpringBootTest
public class AnnUserApiTest {

    @Resource
    private AnnUserApi api;

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
        System.out.println(api.getUser());
    }
}
