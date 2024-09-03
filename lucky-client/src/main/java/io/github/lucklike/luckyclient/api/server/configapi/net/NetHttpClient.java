package io.github.lucklike.luckyclient.api.server.configapi.net;


import com.luckyframework.reflect.Combination;
import io.github.lucklike.httpclient.configapi.ResourceHttpClient;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@ResourceHttpClient(prefix = "#{$class$.getSimpleName()}", source = "``${lucky.server.config-api.url:#{$ann$.url}}/#{$ann$.filename}``")
@Combination(ResourceHttpClient.class)
public @interface NetHttpClient {

    String url() default "http://localhost:8864/config/api";

    String filename() default "#{$class$.getSimpleName()}.properties";
}
