package io.github.lucklike.luckyclient.api.annconvert;

import com.luckyframework.httpclient.proxy.annotations.ObjectGenerate;
import com.luckyframework.httpclient.proxy.annotations.ResultConvertMeta;
import com.luckyframework.reflect.Combination;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Combination({ResultConvertMeta.class})
@ResultConvertMeta(convert = @ObjectGenerate(GsonResponseConvert.class))
public @interface GsonDecoder {

    /**
     * 同 select
     */
    @AliasFor("select")
    String value() default "";

    /**
     * 结果选择表达式，支持SpEL表达式
     */
    @AliasFor("value")
    String select() default "";

    /**
     * 转换元类型
     */
    @AliasFor(annotation = ResultConvertMeta.class, attribute = "metaType")
    Class<?> metaType() default Object.class;
}
