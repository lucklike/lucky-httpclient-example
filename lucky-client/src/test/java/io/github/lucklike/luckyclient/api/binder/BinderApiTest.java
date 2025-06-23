package io.github.lucklike.luckyclient.api.binder;

import com.luckyframework.common.ConfigurationMap;
import org.junit.jupiter.api.Test;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.boot.context.properties.source.ConfigurationPropertyName;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

import javax.annotation.Resource;

@SpringBootTest
public class BinderApiTest {

    @Resource
    Environment environment;

    @Test
    void binderTest() {
        ConfigurationMap configMap = new ConfigurationMap();
        configMap.addProperty("url3", "https://api.luckyclient.com");
        configMap.addProperty("url", "https://api.luckyclient.com/url");
        Binder binder = Binder.get(environment);
        ConfigurationMap configurationMap = binder.bind(ConfigurationPropertyName.adapt("cpe.service.CommonT2", '.'), Bindable.ofInstance(configMap)).orElseGet(() -> configMap);
        System.out.println(configurationMap);
    }
}
