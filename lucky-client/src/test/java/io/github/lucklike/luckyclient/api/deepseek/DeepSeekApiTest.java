package io.github.lucklike.luckyclient.api.deepseek;

import com.luckyframework.common.Console;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author fukang
 * @version 1.0.0
 * @date 2025/2/6 11:01
 */
@SpringBootTest
class DeepSeekApiTest {

    @Resource
    private DeepSeekApi api;

    @Test
    void completions() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            Console.printlnGreen("ME: ");
            String context = scanner.nextLine();
            Console.printlnMulberry("\nAI:");
            api.completions(context);
        }
    }
}