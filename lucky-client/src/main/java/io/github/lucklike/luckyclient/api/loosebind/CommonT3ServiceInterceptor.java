package io.github.lucklike.luckyclient.api.loosebind;

import com.luckyframework.common.StringUtils;
import com.luckyframework.httpclient.core.meta.DefaultRequest;
import com.luckyframework.httpclient.core.meta.Request;
import com.luckyframework.httpclient.proxy.interceptor.Interceptor;
import com.luckyframework.httpclient.proxy.interceptor.InterceptorContext;
import com.luckyframework.httpclient.proxy.url.AnnotationRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author fukang
 * @version 1.0.0
 * @date 2024/8/26 00:42
 */
@Component
public class CommonT3ServiceInterceptor implements Interceptor {

    @Resource
    private BaseParam baseParam;

    @Override
    public void doBeforeExecute(Request request, InterceptorContext context) {
        String path = ((AnnotationRequest) request).getPath();
        ((DefaultRequest) request).setUrlTemplate(StringUtils.joinUrlPath(baseParam.getUrl(), path));
        for (Object argument : context.getContext().getArguments()) {
            if (argument instanceof BaseParam) {
                BeanUtils.copyProperties(baseParam, argument);
            }
        }
    }
}
