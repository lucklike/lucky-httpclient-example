package io.github.lucklike.luckyclient.api.mock;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class IMockApiTest {

    @Resource
    private IMockApi api;

    @Test
    void m200() {
    }

    @Test
    void m404() {
    }

    @Test
    void m307() {
        String s = api.m307();
        System.out.println(s);
    }
}