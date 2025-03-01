package io.github.lucklike.luckyclient.api.deepseek;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author fukang
 * @version 1.0.0
 * @date 2025/2/28 01:56
 */
@SpringBootTest
class DeepSeekApi2Test {

    @Resource
    private  DeepSeekApi2 api;

    @Test
    void completions() {
        api.completions("你好呀，介绍一下你自己，回复大概500字左右");
    }

    @Test
    void completions2() {
        api.completions2("你好呀");
    }

    @Test
    void completions3() {
        api.completions3("你好呀", new DeepSeekEventListener());
    }

    @Test
    void completions4() {
        api.completions4("你好呀");
    }
}