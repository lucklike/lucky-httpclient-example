package io.github.lucklike.discovery.nacos.config;

import com.alibaba.cloud.nacos.ribbon.NacosRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author fukang
 * @version 1.0.0
 * @date 2025/3/13 22:56
 */
@Configuration
public class NacosLoadBalancerConfig {

    @Bean
    public NacosRule nacosRule() {
        return new NacosRule();
    }
}
