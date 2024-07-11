package io.github.lucklike.luckyclient.api.spark;

import com.luckyframework.common.Console;
import com.luckyframework.httpclient.proxy.HttpClientProxyObjectFactory;

import java.util.Scanner;

public class Main {

    public static SparkOpenApi sparkOpenApi = new HttpClientProxyObjectFactory().getJdkProxyObject(SparkOpenApi.class);

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
