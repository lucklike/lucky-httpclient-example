package io.github.lucklike.luckyclient.configuration;

import com.luckyframework.httpclient.core.convert.JsonAutoConvert;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LuckyHttpClientConfiguration {

    @Bean
    public JsonAutoConvert jsonTxtAutoConvert(){
        return new JsonAutoConvert();
    }
}
