package io.github.lucklike.luckyclient.api.cairh.hehe;

import com.luckyframework.httpclient.generalapi.describe.DescribeFunction;
import com.luckyframework.httpclient.proxy.annotations.DomainName;
import com.luckyframework.httpclient.proxy.annotations.ExceptionHandleMeta;
import com.luckyframework.httpclient.proxy.annotations.InterceptorRegister;
import com.luckyframework.httpclient.proxy.annotations.Retryable;
import com.luckyframework.httpclient.proxy.annotations.StaticForm;
import com.luckyframework.httpclient.proxy.spel.SpELImport;

@Retryable
@DomainName("${cpe.service.HeheHttp.url}")
@StaticForm(condition = "#{#matchId($mc$, 'ycv1')}", value = {"app_id=${cpe.service.HeheHttp.appId}", "app_secret=${cpe.service.HeheHttp.appSecret}"})
@SpELImport(DescribeFunction.class)
//@InterceptorRegister(clazz = HeheStandardExceptionHandler.class)
//@ExceptionHandleMeta(clazz = HeheStandardExceptionHandler.class)
public interface HeheBaseApi {

}
