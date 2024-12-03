package io.github.lucklike.luckyclient.api.cairh.annotations;

import com.luckyframework.httpclient.proxy.annotations.RespConvert;
import com.luckyframework.httpclient.proxy.annotations.SSL;
import com.luckyframework.httpclient.proxy.spel.SpELImport;
import io.github.lucklike.httpclient.annotation.HttpClient;
import io.github.lucklike.luckyclient.api.cairh.function.CairhCommonFunction;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static io.github.lucklike.luckyclient.api.cairh.annotations.BaseApi.URL_CONFIG;

/**
 * 注解集成方式
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@SSL
@HttpClient(URL_CONFIG)
@RespConvert("``#{#crh_convert($mc$)}``")
@SpELImport({CairhCommonFunction.class})
public @interface CRHApi {

    @AliasFor(annotation = HttpClient.class, attribute = "name")
    String name() default "";
}
