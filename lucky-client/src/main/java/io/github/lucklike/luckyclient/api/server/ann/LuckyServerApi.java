package io.github.lucklike.luckyclient.api.server.ann;

import com.luckyframework.httpclient.generalapi.describe.ErrorStatusFilter;
import com.luckyframework.httpclient.proxy.annotations.Condition;
import com.luckyframework.httpclient.proxy.annotations.DomainName;
import com.luckyframework.httpclient.proxy.annotations.RespConvert;
import com.luckyframework.httpclient.proxy.context.MethodContext;
import com.luckyframework.httpclient.proxy.spel.var.RootLiteral;
import io.github.lucklike.entity.response.Result;
import org.springframework.core.ResolvableType;

import java.util.HashMap;
import java.util.Map;

import static io.github.lucklike.luckyclient.api.server.ann.LuckyServerApi.DOMAIN_NAME_KEY;

/**
 * @author fukang
 * @version 1.0.0
 * @date 2024/7/7 03:34
 */
@DomainName(DOMAIN_NAME_KEY)
@RespConvert( "``#{#_result_($mc$, $url$)}``")
@ErrorStatusFilter(
        respCodeExp = "#{$body$.code}",
        respCodeAssertExp = "#{$body$.code != 200}",
        statusErrMsgExp = "#{$body$.error}",
        respCodeErrMsgExp = "#{$body$.message}"
)
public interface LuckyServerApi {

    String DOMAIN_NAME_KEY = "${lucky-server.http}";

    static String _result_(MethodContext context, String url) {
        String luckyServer = context.parseExpression(DOMAIN_NAME_KEY, String.class);
        ResolvableType type = context.getReturnResolvableType();

        String returnM = "#{$resp$.getEntity($mc$.getRealMethodReturnType())}";
        String returnD = "#{$body$.data}";

        // 是LuckyAPI
        if (url.startsWith(luckyServer)) {
            if (Result.class.isAssignableFrom(type.resolve())) {
                return returnM;
            }
            return returnD;
        }
        return returnM;
    }
}
