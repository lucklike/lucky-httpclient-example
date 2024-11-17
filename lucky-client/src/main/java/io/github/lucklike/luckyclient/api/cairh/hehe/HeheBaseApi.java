package io.github.lucklike.luckyclient.api.cairh.hehe;

import com.luckyframework.httpclient.generalapi.describe.CommonErrorMsgVars;
import com.luckyframework.httpclient.generalapi.describe.DescribeFunction;
import com.luckyframework.httpclient.proxy.annotations.Condition;
import com.luckyframework.httpclient.proxy.annotations.DomainName;
import com.luckyframework.httpclient.proxy.annotations.RespConvert;
import com.luckyframework.httpclient.proxy.annotations.StaticForm;
import com.luckyframework.httpclient.proxy.spel.SpELImport;
import com.luckyframework.httpclient.proxy.spel.var.ResponseRootVar;
import com.luckyframework.httpclient.proxy.spel.var.RootVar;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


@Condition(assertion = "``#{$assert.status}``", exception = "``#{$err.status}``")
@Condition(
        assertion = "#{#in(errHttpCodes, $body$.error_code)}",
        exception = "``合合接口【#{$api.name}】响应码异常：【#{$body$.error_code}：#{$body$.error_code}】[#{$reqMethod$}] #{$url$}``"
)
@Condition(
        assertion = "#{$body$.result !=null && $body$.result != 0}",
        exception = "``合合接口【#{$api.name}】响应码异常：【#{$body$.result}：#{$body$.info}】[#{$reqMethod$}] #{$url$}``"
)

@RespConvert("#{$body$}")
@SpELImport({DescribeFunction.class, CommonErrorMsgVars.class})
@DomainName("${cpe.service.HeheHttp.url}")
@StaticForm(condition = "#{#matchId($mc$, 'ycv1')}", value = {"app_id=#{appId}", "app_secret=#{appSecret}"})
public interface HeheBaseApi {

    @RootVar(unfold = true)
    Map<String, Object> _var = new HashMap<String, Object>(){{
        put("appId", "${cpe.service.HeheHttp.appId}");
        put("appSecret", "${cpe.service.HeheHttp.appSecret}");
        put("errHttpCodes", Arrays.asList(40001, 40002, 40003, 40004, 40005, 40006, 40007, 40008, 40009, 40010, 50001, 50004, 90099));
    }};


    @ResponseRootVar
    String _statusErrMsg_ = "#{$stringBody$}";

    @RootVar
    int[] _normalStatus_ = {200, 201, 206};
}
