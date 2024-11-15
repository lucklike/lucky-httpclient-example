package io.github.lucklike.luckyclient.api.cairh.function;

import com.luckyframework.httpclient.proxy.context.MethodContext;
import com.luckyframework.httpclient.proxy.spel.Namespace;
import io.github.lucklike.luckyclient.api.cairh.annotations.LooseBind;

/**
 * CRH-OPENAPI公共方法
 */
@Namespace("crh")
public class CairhCommonFunction {

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
