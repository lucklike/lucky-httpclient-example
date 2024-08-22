package io.github.lucklike.luckyclient.api.loosebind;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LooseBindApiTest {

    @Resource
    private LooseBindApi looseBindApi;

    @Test
    void book() {
        System.out.println(looseBindApi.book());
    }
}