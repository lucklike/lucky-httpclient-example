package io.github.lucklike.luckyclient.api.websocket.kdxf;

import com.luckyframework.httpclient.proxy.annotations.Condition;
import com.luckyframework.httpclient.proxy.annotations.JsonParam;
import com.luckyframework.httpclient.proxy.annotations.Post;
import com.luckyframework.httpclient.proxy.annotations.RespConvert;
import com.luckyframework.httpclient.proxy.spel.SpELImport;
import io.github.lucklike.httpclient.discovery.HttpClient;

@SpELImport(KdxfFunctions.class)
@HttpClient("#{@kdxfConfig.httpUrl}")
@Condition(assertion = "#{$status$ != 200}", exception = "#{#kdxf_statusError($status$, $stringBody$)}")
@Condition(assertion = "#{!$body$.code eq '0'}", exception = "#{#kdxf_codeError($body$.code, $body$.msg)}")
public interface HttpApi {

    @JsonParam
    @Post("/initAccess")
    @RespConvert("#{#kdxf_token($body$)}")
    KdxfTokenManager.Token token(String appid, String appkey, String appsecret);

}