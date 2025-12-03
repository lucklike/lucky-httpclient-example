package io.github.lucklike.luckyclient.api.loosebind;

import com.luckyframework.httpclient.core.meta.Response;
import com.luckyframework.httpclient.proxy.annotations.Get;
import com.luckyframework.httpclient.proxy.annotations.RespConvert;
import com.luckyframework.httpclient.proxy.mock.Mock;
import com.luckyframework.httpclient.proxy.mock.MockResponse;
import io.github.lucklike.entity.request.Book;
import io.github.lucklike.httpclient.discovery.HttpClient;

import java.io.IOException;
import java.util.List;

import static com.luckyframework.httpclient.proxy.function.ResourceFunctions.resource;
import static com.luckyframework.httpclient.proxy.function.ResourceFunctions.read;

@HttpClient("http://127.0.0.1:8080")
public interface LooseBindApi {

    @Mock
    @RespConvert("``#{#lbe('$body$.data')}``")
    @Get("/book")
    List<Book> book();

    static Response bookMock() throws IOException {
        return MockResponse.create()
                .json(read(resource("classpath:books.json")));
    }
}
