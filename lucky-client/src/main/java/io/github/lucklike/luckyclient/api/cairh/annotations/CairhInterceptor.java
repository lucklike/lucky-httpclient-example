package io.github.lucklike.luckyclient.api.cairh.annotations;

import com.luckyframework.httpclient.core.meta.Request;
import com.luckyframework.httpclient.proxy.interceptor.Interceptor;
import com.luckyframework.httpclient.proxy.interceptor.InterceptorContext;

import static io.github.lucklike.luckyclient.api.cairh.annotations.BaseApi.URL_CONFIG;

/**
 * @author fukang
 * @version 1.0.0
 * @date 2024/9/13 23:46
 */
public class CairhInterceptor implements Interceptor {

    @Override
    public void doBeforeExecute(Request request, InterceptorContext context) {
        String cairhOpenApi = context.parseExpression(URL_CONFIG, String.class);
        if (!request.getUrl().startsWith(cairhOpenApi)) {
            throw new IllegalArgumentException("Not CRH Open Platform API: " + request.getUrl());
        }

    }
}
