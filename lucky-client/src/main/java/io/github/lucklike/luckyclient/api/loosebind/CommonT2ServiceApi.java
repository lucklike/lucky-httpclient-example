package io.github.lucklike.luckyclient.api.loosebind;

import com.luckyframework.httpclient.core.meta.Response;
import com.luckyframework.httpclient.proxy.annotations.Get;
import com.luckyframework.httpclient.proxy.annotations.HeaderParam;
import com.luckyframework.httpclient.proxy.annotations.JsonBody;
import com.luckyframework.httpclient.proxy.annotations.Post;
import com.luckyframework.httpclient.proxy.annotations.QueryParam;
import com.luckyframework.httpclient.proxy.mock.Mock;
import com.luckyframework.httpclient.proxy.mock.MockResponse;

@Mock
@CommonT2Service
public interface CommonT2ServiceApi {

    @Post("test")
    String test(@JsonBody BaseParam baseParam);

    @Get("query")
    String queryTest(@QueryParam BaseParam baseParam, @HeaderParam BaseParam baseParam2);


    static Response testMock() {
        return MockResponse.create()
                .txt("OK, bang bang bang");
    }

    static Response queryTestMock() {
        return MockResponse.create()
                .xml("<h1>Query Query Query !!!</h1>");
    }
}
