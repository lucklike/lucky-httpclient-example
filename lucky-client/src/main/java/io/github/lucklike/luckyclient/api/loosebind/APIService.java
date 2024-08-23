package io.github.lucklike.luckyclient.api.loosebind;


import com.luckyframework.reflect.Combination;
import io.github.lucklike.httpclient.configapi.LocalConfigHttpClient;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@LocalConfigHttpClient
@Combination(LocalConfigHttpClient.class)
public @interface APIService {

    @AliasFor(annotation = LocalConfigHttpClient.class, attribute = "name")
    String name() default "";
}
