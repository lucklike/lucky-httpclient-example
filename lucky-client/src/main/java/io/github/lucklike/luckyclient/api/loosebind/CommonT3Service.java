package io.github.lucklike.luckyclient.api.loosebind;


import io.github.lucklike.httpclient.annotation.HttpClientComponent;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@HttpClientComponent
//@InterceptorRegister(intercept = @ObjectGenerate(msg = "commonT3ServiceInterceptor"), priority = Integer.MAX_VALUE-3)
//@Combination({InterceptorRegister.class})
public @interface CommonT3Service {

    @AliasFor(annotation = HttpClientComponent.class, attribute = "name")
    String name() default "";
}
