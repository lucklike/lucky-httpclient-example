package io.github.lucklike.luckyclient.api.cairh.annotations;

import com.luckyframework.httpclient.proxy.annotations.DomainName;
import com.luckyframework.httpclient.proxy.annotations.RespConvert;
import com.luckyframework.httpclient.proxy.annotations.SSL;
import com.luckyframework.httpclient.proxy.spel.SpELImport;
import io.github.lucklike.luckyclient.api.cairh.function.CairhCommonFunction;

/**
 * 继承方式集成
 */
@SSL
@RespConvert("``#{#crh_convert($mc$)}``")
@DomainName(BaseApi.URL_CONFIG)
@SpELImport(CairhCommonFunction.class)
public interface BaseApi {


    String URL_CONFIG = "${cairh.openapi.url}";

}
