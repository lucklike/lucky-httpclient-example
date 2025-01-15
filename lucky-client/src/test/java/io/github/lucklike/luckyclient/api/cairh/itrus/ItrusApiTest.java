package io.github.lucklike.luckyclient.api.cairh.itrus;

import io.github.lucklike.luckyclient.api.cairh.itrus.api.ItrusApi;
import io.github.lucklike.luckyclient.api.cairh.itrus.req.CreateUserRequest;
import io.github.lucklike.luckyclient.api.cairh.itrus.resp.CreateUserResponse;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class ItrusApiTest {

    @Resource
    private ItrusApi itrusApi;

    @Test
    void createUser() {
        CreateUserRequest request = new CreateUserRequest();
        request.setDisplayName("刘祥");
        request.setIdCardType("0");
        request.setIdCardNum("541522200305023907");
        request.setType("1");
        request.setPhone("15904836592");
        request.setAuthentication("true");

        CreateUserResponse user = itrusApi.createUser(request);
        System.out.println(user);
    }
}