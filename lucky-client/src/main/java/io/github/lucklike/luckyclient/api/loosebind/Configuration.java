package io.github.lucklike.luckyclient.api.loosebind;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * @author fukang
 * @version 1.0.0
 * @date 2024/8/26 00:58
 */
@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean
    @ConfigurationProperties(prefix = "cpe.service.common-t3")
    public BaseParam t3BaseParam() {
        return new BaseParam();
    }
}
