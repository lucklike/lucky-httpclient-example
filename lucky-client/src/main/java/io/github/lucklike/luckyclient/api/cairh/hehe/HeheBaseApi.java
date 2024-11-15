package io.github.lucklike.luckyclient.api.cairh.hehe;

import com.luckyframework.httpclient.generalapi.describe.DescribeFunction;
import com.luckyframework.httpclient.proxy.annotations.Condition;
import com.luckyframework.httpclient.proxy.annotations.DomainName;
import com.luckyframework.httpclient.proxy.annotations.RespConvert;
import com.luckyframework.httpclient.proxy.annotations.StaticForm;
import com.luckyframework.httpclient.proxy.spel.RootVar;
import com.luckyframework.httpclient.proxy.spel.SpELImport;
import com.luckyframework.httpclient.proxy.spel.VarScope;


@Condition(assertion = "#{$status$ != 200}", exception = "``#{$err.statusErr}``")
@Condition(
        assertion = "#{{40001, 40002, 40003, 40004, 40005, 40006, 40007, 40008, 40009, 40010, 50001, 50004, 90099}.contains($body$.error_code)}",
        exception = "``合合接口【#{$api.name}】响应码异常：【#{$body$.error_code}：#{$body$.error_code}】[#{$reqMethod$}] #{$url$}``"
)
@Condition(
        assertion = "#{$body$.result !=null && $body$.result != 0}",
        exception = "``合合接口【#{$api.name}】响应码异常：【#{$body$.result}：#{$body$.info}】[#{$reqMethod$}] #{$url$}``"
)
@StaticForm(
        condition = "#{$method$.getName() == 'ocrFaceCompare' }",
        value = {"app_id=#{appId}", "app_secret=#{appSecret}"}
)
@RespConvert("#{$body$}")
@SpELImport(DescribeFunction.class)
@DomainName("${cpe.service.HeheHttp.url}")
public interface HeheBaseApi {

    @RootVar
    String appId = "${cpe.service.HeheHttp.appId}";

    @RootVar
    String appSecret = "${cpe.service.HeheHttp.appSecret}";

    @RootVar(literal = true)
    String apiName = "#{#apiName($mc$)}";

    @RootVar(scope = VarScope.RESPONSE)
    String $statusErrMsg = "#{$stringBody$}";
}
