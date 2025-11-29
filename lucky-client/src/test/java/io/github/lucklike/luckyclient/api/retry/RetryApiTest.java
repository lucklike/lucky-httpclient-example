package io.github.lucklike.luckyclient.api.retry;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author fukang
 * @version 1.0.0
 * @date 2025/9/17 01:48
 */
@SpringBootTest
class RetryApiTest {

    @Resource
    private RetryApi retryApi;

    @Test
    void index() {
        retryApi.index();
    }
}