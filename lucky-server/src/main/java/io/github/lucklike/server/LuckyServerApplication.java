package io.github.lucklike.server;

import io.github.lucklike.httpclient.annotation.EnableLuckyHttpClient;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.security.Security;

/**
 * Lucky服务
 *
 * @author fukang
 * @version 1.0.0
 * @date 2024/7/7 02:58
 */
@EnableLuckyHttpClient
@SpringBootApplication
public class LuckyServerApplication {

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    public static void main(String[] args) {
        SpringApplication.run(LuckyServerApplication.class, args);
    }
}
