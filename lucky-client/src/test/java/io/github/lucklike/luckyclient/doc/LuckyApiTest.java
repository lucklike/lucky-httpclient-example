package io.github.lucklike.luckyclient.doc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LuckyApiTest {

    @Autowired
    private LuckyApi luckyApi;

    @Test
    void hello() {
        String hello = luckyApi.text("Jack", 18, "jack@gmail.com");
        System.out.println(hello);
    }
}