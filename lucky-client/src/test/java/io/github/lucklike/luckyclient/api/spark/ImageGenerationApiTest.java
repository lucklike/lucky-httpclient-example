package io.github.lucklike.luckyclient.api.spark;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author fukang
 * @version 1.0.0
 * @date 2025/1/12 04:41
 */
@SpringBootTest
class ImageGenerationApiTest {

    @Resource
    private ImageGenerationApi api;

    @Test
    void imageGenerate() {
        api.imageGenerate("可可爱爱的女孩子", "/Users/fukang/Desktop/test");
    }
}