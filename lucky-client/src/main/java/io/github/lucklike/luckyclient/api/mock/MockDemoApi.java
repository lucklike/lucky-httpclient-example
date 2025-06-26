package io.github.lucklike.luckyclient.api.mock;


import com.luckyframework.httpclient.core.meta.Response;
import com.luckyframework.httpclient.generalapi.describe.TraceFunction;
import com.luckyframework.httpclient.proxy.annotations.Get;
import com.luckyframework.httpclient.proxy.context.MethodContext;
import com.luckyframework.httpclient.proxy.mock.Mock;
import com.luckyframework.httpclient.proxy.mock.MockResponse;
import com.luckyframework.httpclient.proxy.spel.ParameterInfo;
import com.luckyframework.httpclient.proxy.spel.SpELImport;
import com.luckyframework.reflect.AnnotationUtils;
import io.github.lucklike.httpclient.annotation.AllowNull;
import io.github.lucklike.httpclient.discovery.HttpClient;
import io.github.lucklike.httpclient.injection.parameter.ParameterInstanceFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.lang.NonNull;

import java.util.concurrent.Future;


@HttpClient
@SpELImport(TraceFunction.class)
public interface MockDemoApi {

    @Mock(enable = "${baidu.mock.enable: true}")
    @Get("http://www.baidu.com?keyword=#{keyword}")
    String baidu(String keyword);

    @Mock
    @Get("http://www.baidu.com?keyword=#{keyword}")
    Future<String> baidu2(String keyword);

    static Response baidu2$Mock(MethodContext context, MockApi api, ObjectProvider<ParameterInstanceFactory> parameterInstanceFactory) {
        return baidu$Mock(context, api);
    }

    static Response baidu$Mock(MethodContext context, MockApi api) {
        return MockResponse.create()
                .status(200)
                .header("Content-Type", "text/plain; charset=utf-8")
                .header("Service", "Nginx-1.3")
                .header("api", api._404())
                .body("百度一下，你就知道！" + context.getSignature());
    }

}
