package io.github.lucklike.luckyclient.api.spark;

import cn.hutool.core.codec.Base64;
import com.luckyframework.common.NanoIdUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.FileCopyUtils;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
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
        Map<String, Object> idCardInfo = sparkOpenApi.idCardOcr("file:D:/id/card/20240716100959.jpg");
        System.out.println(idCardInfo);
    }

    @Test
    void imageGenerateTest() throws IOException {
        String imageBase64 = sparkOpenApi.imageGenerate("宇宙大爆炸");
        String fileName = NanoIdUtils.randomNanoId() + ".jpg";
        File file = new File("D:/test/" + fileName);
        FileCopyUtils.copy(Base64.decode(imageBase64), file);
        System.out.println(file.getAbsolutePath());
    }
}
