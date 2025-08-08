package io.github.lucklike.discovery.nacos.cairh.api;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CrhTokenApiTest {

    @Resource
    private CrhTokenApi crhTokenApi;

    @Test
    void token() {
        Token token = crhTokenApi.token();
        System.out.println(token);
    }
}