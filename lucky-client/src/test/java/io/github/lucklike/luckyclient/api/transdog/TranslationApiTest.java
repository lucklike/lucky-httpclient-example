package io.github.lucklike.luckyclient.api.transdog;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class TranslationApiTest {

    @Resource
    private TranslationApi tranAp;

    @Test
    void trans() {
        System.out.println(tranAp.trans("你好，世界"));
    }
}
