package io.github.lucklike.discovery.nacos;

import com.alibaba.nacos.spring.context.annotation.discovery.EnableNacosDiscovery;
import io.github.lucklike.httpclient.annotation.EnableLuckyHttpClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@EnableNacosDiscovery
@EnableLuckyHttpClient
@SpringBootApplication
public class NacosClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(NacosClientApplication.class, args);
    }
}
