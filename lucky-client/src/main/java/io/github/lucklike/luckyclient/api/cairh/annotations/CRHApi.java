package io.github.lucklike.luckyclient.api.cairh.annotations;

import com.luckyframework.httpclient.proxy.annotations.Condition;
import com.luckyframework.httpclient.proxy.annotations.InterceptorRegister;
import com.luckyframework.httpclient.proxy.annotations.ObjectGenerate;
import com.luckyframework.httpclient.proxy.annotations.RespConvert;
import com.luckyframework.httpclient.proxy.annotations.SSL;
import com.luckyframework.httpclient.proxy.annotations.StaticHeader;
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
@RespConvert("``#{#convert($mc$)}``")
@SpELImport(fun = CairhCommonFunction.class)
@Condition(assertion = "#{$status$ != 200}", exception = "【财人汇】开放接口访问失败！HTTP状态码：#{$status$}， 接口地址： #{$url$}")
@Condition(assertion = "#{$body$.error.error_no != '0'}", exception = "【财人汇】开放接口访问失败！接口响应码：#{$body$.error.error_no}, 错误信息：#{$body$.error.error_info}，接口地址： #{$url$}")
@StaticHeader("@if(#{#token($mc$)}): Authorization: #{@tokenApi.getAccessToken()}")
@InterceptorRegister(intercept = @ObjectGenerate(CairhInterceptor.class), priority = Integer.MIN_VALUE)
public @interface CRHApi {

    @AliasFor(annotation = HttpClient.class, attribute = "name")
    String name() default "";
}
