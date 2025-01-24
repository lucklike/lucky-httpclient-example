package io.github.lucklike.luckyclient.api.roll;

import com.luckyframework.httpclient.proxy.annotations.Condition;
import com.luckyframework.httpclient.proxy.annotations.RespConvert;
import com.luckyframework.httpclient.proxy.annotations.StaticQuery;
import io.github.lucklike.httpclient.annotation.HttpClient;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Condition(assertion = "#{$status$ != 200}", exception = "【ROLL】油价查询接口调用异常，响应码：'#{$status$}'")
@Condition(assertion = "#{$body$.code != 1}", exception = "【ROLL】油价查询接口调用异常，状态码：'#{$body$.code}', 错误信息：#{$body$.msg}")
@StaticQuery({"app_id=${ROLL.AppID}", "app_secret=${ROLL.AppSecret}"})
@RespConvert
@HttpClient("https://www.mxnzp.com")
public @interface OilHttpClient {

}
