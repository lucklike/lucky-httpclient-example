package io.github.lucklike.luckyclient.api.server.ann;

import com.luckyframework.httpclient.core.meta.Response;
import com.luckyframework.httpclient.proxy.annotations.Branch;
import com.luckyframework.httpclient.proxy.annotations.DomainName;
import com.luckyframework.httpclient.proxy.annotations.RespConvert;
import com.luckyframework.httpclient.proxy.context.MethodContext;
import com.luckyframework.httpclient.proxy.spel.FunctionAlias;
import io.github.lucklike.entity.response.Result;
import org.springframework.core.ResolvableType;

import java.lang.reflect.Type;

/**
 * @author fukang
 * @version 1.0.0
 * @date 2024/7/7 03:34
 */
@DomainName("${lucky-server.http}")
@RespConvert(
        result = "``#{$this$._result_($mc$, $url$)}``",
        conditions = {
                @Branch(assertion = "#{$status$ != 200}", exception = "【Lucky-Server-Api】接口调用异常，响应码: #{$status$}, URL: #{$url$}"),
                @Branch(assertion = "#{$body$.code != 200}", exception = "【Lucky-Server-Api】接口调用异常，code: #{$body$.code}, message: #{$body$.message}, URL: #{$url$}")
        }
)
public interface LuckyServerApi {

    default String _result_(MethodContext context, String url) {
        String luckyServer = context.parseExpression("${lucky-server.http}",String.class);
        ResolvableType type = context.getReturnResolvableType();

        String returnM = "#{$resp$.getEntity($mc$.getRealMethodReturnType())}";
        String returnD = "#{$body$.data}";

        // 是LuckyAPI
        if (url.startsWith(luckyServer)) {
            if (Result.class.isAssignableFrom(type.getRawClass())) {
                return returnM;
            }
            return returnD;
        }
        return returnM;
    }
}
