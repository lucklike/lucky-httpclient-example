package io.github.lucklike.luckyclient.api.ollama;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OllamaApiTest {

    @Resource
    private OllamaApi api;

    @Test
    void generate() {
        api.generate("花为什么是红色的？");
    }
}