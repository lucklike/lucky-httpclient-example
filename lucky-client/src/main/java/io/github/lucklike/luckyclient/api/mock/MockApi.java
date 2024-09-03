package io.github.lucklike.luckyclient.api.mock;

import com.luckyframework.httpclient.proxy.annotations.Get;
import com.luckyframework.httpclient.proxy.mock.MockResponse;
import io.github.lucklike.httpclient.configapi.ResourceHttpClient;

/**
 * @author fukang
 * @version 1.0.0
 * @date 2024/8/17 05:25
 */
@ResourceHttpClient
public interface MockApi {

    @Get("http://www.baidu.com")
    String baidu();

    static MockResponse baiduMock() {
        System.out.println("-----------------------Mock--------------------------");
        return MockResponse.create()
                .status(200)
                .header("Content-Type", "application/json; charset=utf-8")
                .header("Service", "Nginx-1.3")
                .body("百度一下，你就知道！");
    }
}
