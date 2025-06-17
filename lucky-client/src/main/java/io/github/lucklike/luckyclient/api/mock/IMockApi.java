package io.github.lucklike.luckyclient.api.mock;

import com.luckyframework.httpclient.core.meta.Response;
import com.luckyframework.httpclient.proxy.annotations.AutoRedirect;
import com.luckyframework.httpclient.proxy.annotations.Get;
import com.luckyframework.httpclient.proxy.annotations.Post;
import com.luckyframework.httpclient.proxy.context.MethodContext;
import com.luckyframework.httpclient.proxy.mock.Mock;
import com.luckyframework.httpclient.proxy.mock.MockResponse;
import io.github.lucklike.httpclient.discovery.HttpClient;

@AutoRedirect
@HttpClient("http://localhost:8080/mock/")
public interface IMockApi {

    @Mock(
        status = "200",
        header = "Content-Type: text/html",
        body = "><h1>Hello World</h1>"
    )
    @Get("/200")
    String m200(String keyword);

    @Mock(
        status = "404",
        body = "['#{$req$.getURI().getPath()}'] Not Fount!"
    )
    @Post("/404")
    String m404(String api);

    @Mock
    @Get
    String m307();

    static Response m307$Mock(MethodContext context) {
        return MockResponse
                .create()
                .status(307)
                .header("Location", "http://www.baidu.com")
                .txt("Temporary Redirect");
    }
}
