package io.github.lucklike.luckyclient.api.loosebind;

import com.luckyframework.httpclient.core.meta.Response;
import com.luckyframework.httpclient.proxy.annotations.JsonBody;
import com.luckyframework.httpclient.proxy.annotations.Post;
import com.luckyframework.httpclient.proxy.mock.Mock;
import com.luckyframework.httpclient.proxy.mock.MockResponse;

@Mock
@APIService
public interface DefaultParamBindApi {

    @Post("test")
    String test(@JsonBody BaseParam baseParam);


    static Response testMock() {
        return MockResponse.create()
                .txt("OK, bang bang bang");
    }
}
