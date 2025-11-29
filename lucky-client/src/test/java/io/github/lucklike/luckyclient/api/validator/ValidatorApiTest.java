package io.github.lucklike.luckyclient.api.validator;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author fukang
 * @version 1.0.0
 * @date 2025/10/24 01:02
 */
@SpringBootTest
class ValidatorApiTest {

    @Resource
    private ValidatorApi validatorApi;

    @Test
    void validator() {
        String result = validatorApi.validator(Entity.builder().id(1).type("C").name("Jack").email("jack@luckyclient.com").build());
        System.out.println(result);
    }
}