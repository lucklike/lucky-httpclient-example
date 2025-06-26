package io.github.lucklike.luckyclient.api.deepseek.functioncalling.lserver;

import io.github.lucklike.common.api.BookApi;
import io.github.lucklike.common.api.annotation.FunctionCalling;
import io.github.lucklike.common.api.util.FunctionCallMange;
import io.github.lucklike.httpclient.injection.HttpReference;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration
public class FunctionCallConfig {

    @HttpReference
    private BookApi bookApi;

    @Bean
    public FunctionCallMange functionCallMange(ApplicationContext applicationContext) {
        FunctionCallMange functionCallMange = FunctionCallMange.create(bookApi);
        String[] beanNamesForAnnotation = applicationContext.getBeanNamesForAnnotation(FunctionCalling.class);
        for (String funcCallName : beanNamesForAnnotation) {
            functionCallMange.addTool(applicationContext.getBean(funcCallName));
        }
        return functionCallMange;
    }
}
