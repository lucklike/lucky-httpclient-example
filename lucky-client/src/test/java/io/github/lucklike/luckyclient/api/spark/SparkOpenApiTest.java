package io.github.lucklike.luckyclient.api.spark;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Map;

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
        Map<String, Object> idCardInfo = sparkOpenApi.idCardOcr("file:D:/id/card/20240712093345.jpg");
        System.out.println(idCardInfo);
    }
}
