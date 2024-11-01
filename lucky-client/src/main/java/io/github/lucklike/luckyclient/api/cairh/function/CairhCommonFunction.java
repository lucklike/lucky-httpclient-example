package io.github.lucklike.luckyclient.api.cairh.function;

import com.luckyframework.httpclient.proxy.context.MethodContext;
import io.github.lucklike.luckyclient.api.cairh.annotations.LooseBind;
import io.github.lucklike.luckyclient.api.cairh.annotations.NonToken;

/**
 * CRH-OPENAPI公共方法
 */
public class CairhCommonFunction {

    /**
     * 判断某个方法是否需要携带Token信息<br/>
     * 被{@link NonToken @NonToken}标记，且{@link NonToken#value()}为true的方法表示不需要
     * 携带Token信息，其他方法均需要携带Token信息
     *
     * @param mc 当前方法上下文
     * @return 方法是否需要携带Token信息
     */
    public static boolean needToken(MethodContext mc) {
        NonToken nonTokenAnn = mc.getSameAnnotationCombined(NonToken.class);
        return nonTokenAnn == null || !nonTokenAnn.value();
    }

    /**
     * 确定转换表达式，当方法被{@link LooseBind @LooseBind}标注时使用松散绑定
     *
     * @param mc 当前方法上下文
     * @return 转换表达式
     */
    public static String convert(MethodContext mc) {
        LooseBind looseBindAnn = mc.getSameAnnotationCombined(LooseBind.class);
        if (looseBindAnn == null || !looseBindAnn.value()) {
            return "#{$body$.data}";
        }
        return "#{#lb($mc$, $body$.data)}";
    }
}
