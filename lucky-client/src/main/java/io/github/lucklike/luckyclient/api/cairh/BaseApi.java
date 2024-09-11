package io.github.lucklike.luckyclient.api.cairh;

import com.luckyframework.httpclient.proxy.annotations.Condition;
import com.luckyframework.httpclient.proxy.annotations.DomainName;
import com.luckyframework.httpclient.proxy.annotations.RespConvert;
import com.luckyframework.httpclient.proxy.annotations.SSL;
import com.luckyframework.httpclient.proxy.annotations.StaticHeader;
import com.luckyframework.httpclient.proxy.context.MethodContext;
import io.github.lucklike.luckyclient.api.cairh.annotations.LooseBind;
import io.github.lucklike.luckyclient.api.cairh.annotations.NonToken;

@SSL
@RespConvert("``#{#convert($mc$)}``")
@Condition(assertion = "#{$status$ != 200}", exception = "【财人汇】开放接口访问失败！HTTP状态码：#{$status$}， 接口地址： #{$url$}")
@Condition(assertion = "#{$body$.error.error_no != '0'}", exception = "【财人汇】开放接口访问失败！接口响应码：#{$body$.error.error_no}, 错误信息：#{$body$.error.error_info}，接口地址： #{$url$}")
@StaticHeader("@if(#{#token($mc$)}): Authorization: #{@tokenApi.getAccessToken()}")
@DomainName("${cairh.openapi.url}")
public interface BaseApi {


    /**
     * 判断某个方法是否需要携带Token信息<br/>
     * 被{@link NonToken @NonToken}标记，且{@link NonToken#value()}为true的方法表示不需要
     * 携带Token信息，其他方法均需要携带Token信息
     *
     * @param mc 当前方法上下文
     * @return 方法是否需要携带Token信息
     */
    static boolean token(MethodContext mc) {
        NonToken nonTokenAnn = mc.getSameAnnotationCombined(NonToken.class);
        return nonTokenAnn == null || !nonTokenAnn.value();
    }

    /**
     * 确定转换表达式，当方法被{@link LooseBind @LooseBind}标注时使用松散绑定
     *
     * @param mc 当前方法上下文
     * @return 转换表达式
     */
    static String convert(MethodContext mc) {
        LooseBind looseBindAnn = mc.getSameAnnotationCombined(LooseBind.class);
        if (looseBindAnn == null || !looseBindAnn.value()) {
            return "#{$body$.data}";
        }
        return "#{#lb($mc$, $body$.data)}";
    }

}
