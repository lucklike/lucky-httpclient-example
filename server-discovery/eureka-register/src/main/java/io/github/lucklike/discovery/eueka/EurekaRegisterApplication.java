package io.github.lucklike.discovery.eueka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Hello world!
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaRegisterApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaRegisterApplication.class, args);
    }
}
