package io.github.lucklike.luckyclient;

import com.alibaba.nacos.spring.context.annotation.discovery.EnableNacosDiscovery;
import io.github.lucklike.httpclient.annotation.EnableLuckyHttpClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableNacosDiscovery
@EnableAspectJAutoProxy
@EnableLuckyHttpClient
@SpringBootApplication
public class LuckyClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(LuckyClientApplication.class, args);
    }

}
