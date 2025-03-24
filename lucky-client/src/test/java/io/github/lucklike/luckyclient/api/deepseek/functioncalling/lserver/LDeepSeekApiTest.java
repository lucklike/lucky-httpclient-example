package io.github.lucklike.luckyclient.api.deepseek.functioncalling.lserver;

import com.luckyframework.common.Console;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class LDeepSeekApiTest {

    @Resource
    private LDeepSeekApi api;

    @Test
    void completions() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            Console.printlnGreen("ME: ");
            String context = scanner.nextLine();
            Console.printlnMulberry("\nDeepSeek:");
            Console.printlnMulberry(api.completions(context));
        }
    }
}