package io.github.lucklike.luckyclient.api.http2;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author fukang
 * @version 1.0.0
 * @date 2025/9/6 04:20
 */
@SpringBootTest
class Http2ApiTest {

    @Resource
    private Http2Api http2Api;

    @Test
    void index() {
        String index = http2Api.index();
        System.out.println(index);
    }
}