package io.github.lucklike.luckyclient.configuration;

import com.luckyframework.httpclient.core.convert.JsonAutoConvert;
import com.luckyframework.httpclient.core.meta.Response;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LuckyHttpClientConfiguration {

    @Bean
    public Response.AutoConvert jsonTxtAutoConvert(){
        return new JsonAutoConvert();
    }
}
