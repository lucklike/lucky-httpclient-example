package io.github.lucklike.luckyclient.api.server;

import io.github.lucklike.entity.request.User;
import io.github.lucklike.entity.response.Result;
import io.github.lucklike.luckyclient.api.server.configapi.UserApi;
import io.github.lucklike.luckyclient.api.server.configapi.net.NetUserApi;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author fukang
 * @version 1.0.0
 * @date 2024/7/9 01:47
 */
@SpringBootTest
public class UserApiTest {

    @Resource
    private UserApi api;

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

    @Resource
    private NetUserApi netUserApi;

    @Test
    void netPostUserTest() {
        User user = new User();
        user.setId(1);
        user.setName("Lucky的猫");
        user.setPassword("P-A-$-$-W-0-R-D");
        user.setPhone("13456789925");
        user.setEmail("13456789925@qq.com");
        User userResult = netUserApi.postUser(user);

        System.out.println(userResult);
    }
}
