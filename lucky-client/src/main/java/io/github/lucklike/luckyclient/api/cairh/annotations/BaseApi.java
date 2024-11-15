package io.github.lucklike.luckyclient.api.cairh.annotations;

import com.luckyframework.httpclient.proxy.annotations.Condition;
import com.luckyframework.httpclient.proxy.annotations.DomainName;
import com.luckyframework.httpclient.proxy.annotations.InterceptorRegister;
import com.luckyframework.httpclient.proxy.annotations.ObjectGenerate;
import com.luckyframework.httpclient.proxy.annotations.RespConvert;
import com.luckyframework.httpclient.proxy.annotations.SSL;
import com.luckyframework.httpclient.proxy.annotations.StaticHeader;
import com.luckyframework.httpclient.proxy.spel.SpELImport;
import io.github.lucklike.luckyclient.api.cairh.function.CairhCommonFunction;

/**
 * 继承方式集成
 */
@SSL
@RespConvert("``#{#crh_convert($mc$)}``")
@Condition(assertion = "#{$status$ != 200}", exception = "【财人汇】开放接口访问失败！HTTP状态码：#{$status$}， 接口地址： #{$url$}")
@Condition(assertion = "#{$body$.error.error_no != '0'}", exception = "【财人汇】开放接口访问失败！接口响应码：#{$body$.error.error_no}, 错误信息：#{$body$.error.error_info}，接口地址： #{$url$}")
@StaticHeader("@if(#{!#matchId($mc$, 'CRH_TOKEN_API')}): Authorization: #{@tokenApi.getAccessToken()}")
@DomainName(BaseApi.URL_CONFIG)
@SpELImport(CairhCommonFunction.class)
@InterceptorRegister(intercept = @ObjectGenerate(CairhInterceptor.class), priority = Integer.MIN_VALUE)
public interface BaseApi {


    String URL_CONFIG = "${cairh.openapi.url}";

}
