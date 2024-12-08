package io.github.lucklike.luckyclient.api.mock;


import com.luckyframework.httpclient.core.meta.Response;
import com.luckyframework.httpclient.generalapi.describe.TraceFunction;
import com.luckyframework.httpclient.proxy.annotations.Get;
import com.luckyframework.httpclient.proxy.context.MethodContext;
import com.luckyframework.httpclient.proxy.mock.Mock;
import com.luckyframework.httpclient.proxy.mock.MockResponse;
import com.luckyframework.httpclient.proxy.spel.SpELImport;
import io.github.lucklike.httpclient.annotation.HttpClient;


@HttpClient
@SpELImport(TraceFunction.class)
public interface MockDemoApi {

    @Mock(enable = "${baidu.mock.enable: false}")
    @Get("http://www.baidu.com?keyword=#{keyword}")
    String baidu(String keyword);

    static Response baiduMock(MethodContext context) {
        return MockResponse.create()
                .status(200)
                .header("Content-Type", "text/plain; charset=utf-8")
                .header("Service", "Nginx-1.3")
                .body("百度一下，你就知道！" + context.getSignature());
    }

}
