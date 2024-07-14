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
        Map<String, Object> idCardInfo = sparkOpenApi.idCardOcr("file:/Users/fukang/Desktop/WechatIMG6146.jpg");
        System.out.println(idCardInfo);
    }

    @Test
    void imageGenerateTest() {
        String imaged = sparkOpenApi.imageGenerate("帮我画一副山水画");
        System.out.println(imaged);
    }
}
