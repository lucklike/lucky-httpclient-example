package io.github.lucklike.luckyclient.api;

import io.github.lucklike.luckyclient.api.lucky.LuckyApi;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LuckyApiTest {

    @Autowired
    private LuckyApi luckyApi;

    @Test
    void testBenediction() {
        luckyApi.benediction();
    }
}
