package io.github.lucklike.luckyclient.api.simple;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class BilibiliApiTest {

    @Resource
    private BilibiliApi bilibiliApi;

    @Test
    void indexTest() {
        while (true) {
           bilibiliApi.index("hello log");
        }

    }

}
