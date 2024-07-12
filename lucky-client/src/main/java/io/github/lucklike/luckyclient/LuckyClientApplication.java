package io.github.lucklike.luckyclient;

import io.github.lucklike.httpclient.annotation.EnableLuckyHttpClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableLuckyHttpClient
@SpringBootApplication
public class LuckyClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(LuckyClientApplication.class, args);
    }

}