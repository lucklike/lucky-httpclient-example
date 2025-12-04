package io.github.lucklike.luckyclient.api.yunxi;

import com.luckyframework.httpclient.generalapi.AutoVerifyHttpStatus;
import com.luckyframework.httpclient.generalapi.describe.Describe;
import com.luckyframework.httpclient.proxy.annotations.Condition;
import com.luckyframework.httpclient.proxy.annotations.RespConvert;
import com.luckyframework.httpclient.proxy.annotations.StaticQuery;
import io.github.lucklike.httpclient.discovery.HttpClient;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 云析API(http://api.zhyunxi.com)
 *
 * @author fukang
 * @version 1.0.0
 * @date 2025/12/5 00:37
 */
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Describe("云析API")
@RespConvert
@AutoVerifyHttpStatus
@Condition(assertion = "#{$body$.code != 0}", exception = "#{bizErr('[{}][{}][{}]接口调用异常，code={}, msg={}', $req$.getRequestMethod(), $req$.getUrl(), $api$.name, $body$.code, $body$.msg)}")
@StaticQuery("key=${lucky.http-server.yun-xi.key}")
@HttpClient("${lucky.http-server.yun-xi.url}")
public @interface YunXiClient {
}
