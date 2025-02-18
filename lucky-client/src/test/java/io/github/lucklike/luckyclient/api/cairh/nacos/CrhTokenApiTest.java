package io.github.lucklike.luckyclient.api.cairh.nacos;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CrhTokenApiTest {

    @Resource
    private CrhTokenApi api;

    @Test
    void getAccessToken() {
        System.out.println(api.getAccessToken());
        System.out.println(api.getAccessToken());
    }
}