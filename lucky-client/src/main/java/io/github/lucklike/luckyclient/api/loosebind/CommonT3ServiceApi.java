package io.github.lucklike.luckyclient.api.loosebind;

import com.luckyframework.common.StringUtils;
import com.luckyframework.httpclient.core.meta.DefaultRequest;
import com.luckyframework.httpclient.core.meta.Request;
import com.luckyframework.httpclient.core.meta.Response;
import com.luckyframework.httpclient.proxy.annotations.Get;
import com.luckyframework.httpclient.proxy.annotations.JsonBody;
import com.luckyframework.httpclient.proxy.context.MethodContext;
import com.luckyframework.httpclient.proxy.mock.Mock;
import com.luckyframework.httpclient.proxy.mock.MockResponse;
import com.luckyframework.httpclient.proxy.spel.hook.Lifecycle;
import com.luckyframework.httpclient.proxy.spel.hook.callback.Callback;
import com.luckyframework.httpclient.proxy.url.AnnotationRequest;
import org.springframework.beans.BeanUtils;

/**
 * @author fukang
 * @version 1.0.0
 * @date 2024/8/26 00:45
 */
@CommonT3Service
public interface CommonT3ServiceApi {

    @Mock
    @Get("/test")
    String test(@JsonBody T3Request request);

    static Response testMock() {
        return MockResponse.create()
                .txt("OK");
    }

    @Callback(lifecycle = Lifecycle.REQUEST_INIT)
    public static void addDefaultParam(Request request, MethodContext context, BaseParam baseParam) {
        String path = ((AnnotationRequest) request).getPath();
        ((DefaultRequest) request).setUrlTemplate(StringUtils.joinUrlPath(baseParam.getUrl(), path));
        for (Object argument : context.getArguments()) {
            if (argument instanceof BaseParam) {
                BeanUtils.copyProperties(baseParam, argument);
            }
        }
    }
}
