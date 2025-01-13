package io.github.lucklike.luckyclient.api.cairh.itrus;

import io.github.lucklike.luckyclient.api.cairh.itrus.req.ItrusContractCreateUserRequest;
import io.github.lucklike.luckyclient.api.cairh.itrus.resp.CreateUserResponse;
import io.github.lucklike.luckyclient.api.cairh.itrus.resp.ItrusBaseResponse;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ItrusApiTest {

    @Resource
    private ItrusApi itrusApi;

    @Test
    void createUser() {
        ItrusContractCreateUserRequest request = new ItrusContractCreateUserRequest();
        request.setDisplayName("刘祥");
        request.setIdCardType("0");
        request.setIdCardNum("541522200305023907");
        request.setType("1");
        request.setPhone("15904836592");
        request.setAuthentication("true");

        Optional<CreateUserResponse> userOptional = itrusApi.createUser(request);
        System.out.println(userOptional);
    }
}