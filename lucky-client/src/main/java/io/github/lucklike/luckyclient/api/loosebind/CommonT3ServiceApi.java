package io.github.lucklike.luckyclient.api.loosebind;

import com.luckyframework.httpclient.core.meta.Response;
import com.luckyframework.httpclient.proxy.annotations.Get;
import com.luckyframework.httpclient.proxy.mock.Mock;
import com.luckyframework.httpclient.proxy.mock.MockResponse;

/**
 * @author fukang
 * @version 1.0.0
 * @date 2024/8/26 00:45
 */
@CommonT3Service
public interface CommonT3ServiceApi {

    @Mock
    @Get("/test")
    String test();

    static Response testMock() {
        return MockResponse.create()
                .txt("OK");
    }
}
