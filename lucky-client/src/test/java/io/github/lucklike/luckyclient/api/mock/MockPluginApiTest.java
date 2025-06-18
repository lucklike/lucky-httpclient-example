package io.github.lucklike.luckyclient.api.mock;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MockPluginApiTest {

    @Resource
    private MockPluginApi mockPluginApi;

    @Test
    void test1() {
        System.out.println(mockPluginApi);
        String s = mockPluginApi.test1();

        System.out.println(s);
    }

    @Test
    void agsTest() {
        System.out.println(mockPluginApi.toString());
        String s = mockPluginApi.agsTest("12v你哈", 12);
        System.out.println(s);
    }


    @Test
    void test2() {
        String s = mockPluginApi.test2();
        System.out.println(s);
    }
}