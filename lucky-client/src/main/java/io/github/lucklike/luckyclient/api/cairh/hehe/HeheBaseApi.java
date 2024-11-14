package io.github.lucklike.luckyclient.api.cairh.hehe;

import com.luckyframework.httpclient.proxy.annotations.Condition;
import com.luckyframework.httpclient.proxy.annotations.DomainName;
import com.luckyframework.httpclient.proxy.annotations.RespConvert;
import com.luckyframework.httpclient.proxy.annotations.StaticForm;
import com.luckyframework.httpclient.proxy.spel.RootVar;
import com.luckyframework.httpclient.proxy.spel.RootVarLit;
import com.luckyframework.httpclient.proxy.spel.SpELImport;
import io.github.lucklike.luckyclient.api.cairh.ApiNameUtils;


@Condition(
        assertion = "#{$status$ != 200}",
        exception = "``合合接口【#{apiName}】HTTP状态码异常：【#{$status$}】[#{$reqMethod$}] #{$url$}``"
)
@Condition(
        assertion = "#{{40001, 40002, 40003, 40004, 40005, 40006, 40007, 40008, 40009, 40010, 50001, 50004, 90099}.contains($body$.error_code)}",
        exception = "``合合接口【#{apiName}】响应码异常：【#{$body$.error_code}：#{$body$.error_code}】[#{$reqMethod$}] #{$url$}``"
)
@Condition(
        assertion = "#{$body$.result !=null && $body$.result != 0}",
        exception = "``合合接口【#{apiName}】响应码异常：【#{$body$.result}：#{$body$.info}】[#{$reqMethod$}] #{$url$}``"
)
@StaticForm(
        condition = "#{$method$.getName() == 'ocrFaceCompare' }",
        value = {"app_id=#{appId}", "app_secret=#{appSecret}"}
)
@RespConvert("#{$body$}")
@SpELImport(ApiNameUtils.class)
@DomainName("${cpe.service.HeheHttp.url}")
public interface HeheBaseApi {

    @RootVar
    String appId = "${cpe.service.HeheHttp.appId}";

    @RootVar
    String appSecret = "${cpe.service.HeheHttp.appSecret}";

    @RootVarLit
    String apiName = "#{#apiName($mc$)}";
}
