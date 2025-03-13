package io.github.lucklike.discovery.consul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Hello world!
 */
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = "io.github.lucklike")
public class ConsulServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsulServerApplication.class, args);
    }
}
