package io.github.lucklike.luckyclient.api.cairh;


import com.luckyframework.httpclient.proxy.context.MethodContext;

public class ApiNameUtils {

    public static String apiName(MethodContext context) {
        ApiName apiNameAnn = context.getMergedAnnotation(ApiName.class);
        if (apiNameAnn != null) {
            return apiNameAnn.value();
        }
        return context.getCurrentAnnotatedElement().getName();
    }
}
