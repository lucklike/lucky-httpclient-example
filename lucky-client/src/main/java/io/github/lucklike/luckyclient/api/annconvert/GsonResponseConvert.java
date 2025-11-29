package io.github.lucklike.luckyclient.api.annconvert;

import com.google.gson.Gson;
import com.luckyframework.common.StringUtils;
import com.luckyframework.httpclient.core.meta.Response;
import com.luckyframework.httpclient.proxy.convert.ConvertContext;
import com.luckyframework.httpclient.proxy.convert.ResponseConvert;
import com.luckyframework.spel.LazyValue;

import java.lang.reflect.Type;

public class GsonResponseConvert implements ResponseConvert {

    private final Gson gson = new Gson();

    @Override
    public <T> T convert(Response response, ConvertContext context) throws Throwable {
        // 获取GsonDecoder注解实例
        GsonDecoder gsonDecoderAnn = context.toAnnotation(GsonDecoder.class);

        Type methodReturnType = context.getContext().getResultType();
        String select = gsonDecoderAnn.select();
        if (StringUtils.hasText(select)) {
            // 获取转换元类型
            Type convertMetaType = context.getConvertMetaType();
            // 向上下文变量中添加一个Root变量$gdata$
            context.getContextVar().addRootVariable("$gdata$", LazyValue.of(() -> gson.fromJson(response.getStringResult(), convertMetaType)));
            // 运行结果选择表达式，返回结果
            return context.parseExpression(select, methodReturnType);

        } else {
            return gson.fromJson(response.getStringResult(), methodReturnType);
        }
    }
}
