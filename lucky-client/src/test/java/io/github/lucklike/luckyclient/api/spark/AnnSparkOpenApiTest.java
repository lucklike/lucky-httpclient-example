package io.github.lucklike.luckyclient.api.spark;

import cn.hutool.core.codec.Base64;
import com.luckyframework.common.Console;
import com.luckyframework.common.NanoIdUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.FileCopyUtils;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

/**
 * @author fukang
 * @version 1.0.0
 * @date 2024/7/12 01:18
 */
@SpringBootTest
public class AnnSparkOpenApiTest {

    @Resource
    private AnnSparkOpenApi annSparkOpenApi;

    @Test
    void completionsTest() {
        annSparkOpenApi.completions("你是谁？");
//        Scanner src = new Scanner(System.in);
//        while (true) {
//            Console.printlnGreen("ME:");
//            String input = src.nextLine();
//            Console.printlnMulberry("\nAI:");
//            annSparkOpenApi.completions(input);
//        }
    }

    @Test
    void idCardOcrTest() {
        Map<String, Object> idCardInfo = annSparkOpenApi.idCardOcr("file:D:/id/card/20240719110322.jpg");
        System.out.println(idCardInfo);
    }

    @Test
    void imageGenerateTest() throws IOException {
        String content = "失恋的伤心女该";
        String imageBase64 = annSparkOpenApi.imageGenerate(content);
        String fileName = content + "_" +NanoIdUtils.randomNanoId(3) + ".jpg";
        File file = new File("D:/test/" + fileName);
        FileCopyUtils.copy(Base64.decode(imageBase64), file);
        System.out.println(file.getAbsolutePath());
    }
}
