package io.github.lucklike.luckyclient.api.server.ann;

import com.luckyframework.httpclient.core.meta.ClientCookie;
import com.luckyframework.httpclient.core.meta.ContentType;
import com.luckyframework.httpclient.proxy.annotations.Get;
import com.luckyframework.httpclient.proxy.mock.Mock;
import com.luckyframework.httpclient.proxy.mock.MockResponse;
import com.luckyframework.io.MultipartFile;
import io.github.lucklike.entity.response.Result;
import io.github.lucklike.httpclient.annotation.HttpClientComponent;

/**
 * @author fukang
 * @version 1.0.0
 * @date 2024/7/7 03:35
 */
@HttpClientComponent
public interface HelloWorldApi extends LuckyServerApi {


    @Mock(
        status = 404,
        header = "Content-Type: text/plain",
        body = "Hello"
    )
    @Get("/hello")
    String hello();

    @Mock
    @Get("/exception")
    Result<Object> exception();

    @Get("http://www.baidu.com")
    String baidu();

    @Mock(body = "#{#resource('file:${user.dir}/kt_token.json')}")
    @Get("http://localhost:8080/getFile")
    MultipartFile getFile();

    static MockResponse exceptionMock() {
        ClientCookie cookie = new ClientCookie("NAME", "FUKANG");
        cookie.setComment("username");
        cookie.setMaxAge(10000);
        cookie.setHttpOnly(true);
        return MockResponse.create()
                .status(500)
                .cookie("BIDUPSID=DA9ED3306B7BBCF49B26FC7AB2172567; expires=Thu, 31-Dec-37 23:55:55 GMT; max-age=2147483647; path=/; domain=.baidu.com")
                .cookie("BAIDUID=DA9ED3306B7BBCF49B26FC7AB2172567:FG=1; expires=Thu, 31-Dec-37 23:55:55 GMT; max-age=2147483647; path=/; domain=.baidu.com")
                .cookie(cookie)
                .json(Result.fail(500, "exception"));
    }

}
