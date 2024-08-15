package io.github.lucklike.luckyclient.api.baiduai;

import com.luckyframework.common.Console;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Scanner;

@SpringBootTest
public class BaiduAITest {

    @Resource
    private BaiduAI baiduAI;

    @Test
    void getTokenTest() {
        Token token = baiduAI.getToken();
        System.out.println(token);
    }

    @Test
    void questionsAndAnswersTest() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            Console.printlnGreen("ME: ");
            String context = scanner.nextLine();
            Console.printlnMulberry("\nAI:");
            baiduAI.questionsAndAnswers(context);
        }
    }
}
