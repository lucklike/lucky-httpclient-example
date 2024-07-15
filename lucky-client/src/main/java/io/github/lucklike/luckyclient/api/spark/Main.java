package io.github.lucklike.luckyclient.api.spark;

import com.luckyframework.common.Console;
import com.luckyframework.httpclient.proxy.HttpClientProxyObjectFactory;
import io.github.lucklike.luckyclient.LuckyClientApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;

public class Main {
    private final static ApplicationContext applicationContext = new AnnotationConfigApplicationContext(LuckyClientApplication.class);
    private final static SparkOpenApi sparkOpenApi = applicationContext.getBean(SparkOpenApi.class);

    public static void main(String[] args) {
        Scanner src = new Scanner(System.in);
        while (true) {
            Console.printlnGreen("ME:");
            String input = src.nextLine();
            System.out.println("\nAI:");
            sparkOpenApi.completions(input);
        }

    }
}
