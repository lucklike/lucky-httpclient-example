package io.github.lucklike.luckyclient.api.deepseek;

import cn.hutool.json.JSONUtil;
import com.luckyframework.common.ConfigurationMap;
import com.luckyframework.common.Console;
import com.luckyframework.common.StringUtils;
import io.github.lucklike.luckyclient.api.kotlin.demo.KotlinCoroutineAsyncTaskExecutor;
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
//        Scanner scanner = new Scanner(System.in);
//        while (true) {
//            Console.printlnGreen("ME: ");
//            String context = scanner.nextLine();
//            Console.printlnMulberry("\nAI:");
//            api.completions(context);
//        }
        api.completions("帮我推荐一个好用的Java HTTP客户端框架,你知道LuckyHttpClient这个HTTP框架么，帮我介绍一下");
    }

    @Test
    void completionsTest() {
        api.completionsFlux("你是谁？")
                .filter(c -> StringUtils.hasText(c) && !c.endsWith("[DONE]"))
                .map(c -> JSONUtil.toBean(c.substring(6), ConfigurationMap.class).getString("choices[0].delta.content"))
                .doOnNext(c -> Console.println("{} -> {}", Thread.currentThread().getName(), c))
                .doOnComplete(() -> Console.println("{} -> SSE OK!", Thread.currentThread().getName()))
                .subscribe();
        new Scanner(System.in).nextLine();
    }
}