package io.github.lucklike.luckyclient.api.server.ann;

import io.github.lucklike.entity.request.bytes.ByteJson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author fukang
 * @version 1.0.0
 * @date 2025/1/15 01:18
 */
@SpringBootTest
class ByteJsonApiTest {

    @Autowired
    private ByteJsonApi api;

    @Test
    void byteJson() {
        byte[] byteJson = api.byteJson();
        System.out.println(byteJson);
    }
}