package io.github.lucklike.luckyclient.api.spark;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author fukang
 * @version 1.0.0
 * @date 2024/7/12 01:18
 */
@SpringBootTest
public class SparkOpenApiTest {

    @Resource
    private SparkOpenApi sparkOpenApi;

    @Test
    void idCardOcrTest() {
        System.out.println(sparkOpenApi.idCardOcr("file:/Users/fukang/Desktop/WechatIMG6044.jpg"));
    }
}
