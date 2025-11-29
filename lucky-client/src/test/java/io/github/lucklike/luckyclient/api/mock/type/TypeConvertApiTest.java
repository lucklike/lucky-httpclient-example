package io.github.lucklike.luckyclient.api.mock.type;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author fukang
 * @version 1.0.0
 * @date 2025/11/4 01:36
 */
@SpringBootTest
class TypeConvertApiTest {

    @Resource
    private TypeConvertApi api;

    @Test
    void getUserById() {
        User userById = api.getUserById(1);
        System.out.println(userById);
    }

    @Test
    void getUsers() {
        List<User> users = api.getUsers();
        System.out.println(users);
    }
}