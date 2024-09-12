package io.github.lucklike.luckyclient.api.cairh.annotations;

import com.luckyframework.httpclient.proxy.annotations.Condition;
import com.luckyframework.httpclient.proxy.annotations.DomainName;
import com.luckyframework.httpclient.proxy.annotations.RespConvert;
import com.luckyframework.httpclient.proxy.annotations.SSL;
import com.luckyframework.httpclient.proxy.annotations.StaticHeader;
import com.luckyframework.httpclient.proxy.spel.SpELImport;
import io.github.lucklike.luckyclient.api.cairh.function.CairhCommonFunction;

/**
 * 继承方式集成
 */
@SSL
@RespConvert("``#{#convert($mc$)}``")
@Condition(assertion = "#{$status$ != 200}", exception = "【财人汇】开放接口访问失败！HTTP状态码：#{$status$}， 接口地址： #{$url$}")
@Condition(assertion = "#{$body$.error.error_no != '0'}", exception = "【财人汇】开放接口访问失败！接口响应码：#{$body$.error.error_no}, 错误信息：#{$body$.error.error_info}，接口地址： #{$url$}")
@StaticHeader("@if(#{#token($mc$)}): Authorization: #{@tokenApi.getAccessToken()}")
@DomainName("${cairh.openapi.url}")
@SpELImport(fun = CairhCommonFunction.class)
public interface BaseApi {


}
