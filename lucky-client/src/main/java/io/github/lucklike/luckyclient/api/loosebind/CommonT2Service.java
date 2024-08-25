package io.github.lucklike.luckyclient.api.loosebind;


import com.luckyframework.reflect.Combination;
import io.github.lucklike.httpclient.configapi.SpringEnvHttpClient;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@SpringEnvHttpClient(prefix = "cpe.service.CommonT2")
@Combination(SpringEnvHttpClient.class)
public @interface CommonT2Service {

    @AliasFor(annotation = SpringEnvHttpClient.class, attribute = "name")
    String name() default "";
}
