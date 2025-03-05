package io.github.lucklike.luckyclient.api.annconvert;

import io.github.lucklike.entity.request.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RespConvertApiTest {

    @Resource
    private RespConvertApi respConvertApi;

    @Test
    void getUsers() {
        List<User> users = respConvertApi.getUsers();
        users.forEach(System.out::println);
    }

    @Test
    void getAdultUsers() {
        List<User> adultUsers = respConvertApi.getAdultUsers();
        adultUsers.forEach(System.out::println);
    }

    @Test
    void getAllUseId() {
        List<Integer> ids = respConvertApi.getAllUseId();
        System.out.println(ids);
    }

    @Test
    void gsonDecoderTest() {
        User users = respConvertApi.gsonDecoderFindById(2);
        System.out.println(users);
    }
}