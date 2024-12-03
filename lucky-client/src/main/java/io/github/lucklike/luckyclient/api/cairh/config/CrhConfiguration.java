package io.github.lucklike.luckyclient.api.cairh.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CrhConfiguration {


    @Bean
    @ConfigurationProperties(prefix = "cairh.openapi")
    public CrhOpenApiProperties crhOpenApiProperties() {
        return new CrhOpenApiProperties();
    }

}
