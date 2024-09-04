package io.github.lucklike.luckyclient.api.abstractapi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author fukang
 * @version 1.0.0
 * @date 2024/9/4 21:14
 */
@SpringBootTest
class BaiduAITest {

    @Autowired
    private BaiduAI baiduAI;

    @Test
    void questionsAndAnswersTest() {
        baiduAI.questionsAndAnswersTest();
    }
}