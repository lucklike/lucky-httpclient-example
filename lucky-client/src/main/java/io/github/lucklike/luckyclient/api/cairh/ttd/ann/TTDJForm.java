package io.github.lucklike.luckyclient.api.cairh.ttd.ann;

import com.luckyframework.reflect.Param;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author fukang
 * @version 1.0.0
 * @date 2025/2/16 02:21
 */
@Target({ElementType.PARAMETER, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Param
public @interface TTDJForm {

    @AliasFor(annotation = Param.class, attribute = "value")
    String value() default "";
}
