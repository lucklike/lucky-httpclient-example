package io.github.lucklike.discivery.zookeeper;

import io.github.lucklike.httpclient.annotation.EnableLuckyHttpClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Hello world!
 */
@EnableLuckyHttpClient
@EnableDiscoveryClient
@SpringBootApplication
public class ZookeeperClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZookeeperClientApplication.class, args);
    }
}
