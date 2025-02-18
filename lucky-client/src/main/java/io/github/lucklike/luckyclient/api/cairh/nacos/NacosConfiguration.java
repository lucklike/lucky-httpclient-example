package io.github.lucklike.luckyclient.api.cairh.nacos;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import io.github.lucklike.luckyclient.nacos.NacosConfigProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class NacosConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "crh.nacos")
    public NacosConfigProperties crhNacosConfigProperties() {
        return new NacosConfigProperties();
    }

    @Bean
    public NamingService crhNamingService(NacosConfigProperties config) throws NacosException {
        Properties properties = new Properties();
        properties.setProperty(PropertyKeyConst.SERVER_ADDR, config.getServerAddr());
        properties.setProperty(PropertyKeyConst.NAMESPACE, config.getNamespace());
        properties.setProperty(PropertyKeyConst.USERNAME, config.getUsername());
        properties.setProperty(PropertyKeyConst.PASSWORD, config.getPassword());
        return NacosFactory.createNamingService(properties);
    }

}
