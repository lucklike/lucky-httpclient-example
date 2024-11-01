package io.github.lucklike.luckyclient.api.server.ann;

import com.luckyframework.httpclient.proxy.annotations.Get;
import com.luckyframework.httpclient.proxy.annotations.JsonBody;
import com.luckyframework.httpclient.proxy.annotations.ObjectGenerate;
import com.luckyframework.httpclient.proxy.annotations.Post;
import com.luckyframework.httpclient.proxy.annotations.RespConvert;
import com.luckyframework.httpclient.proxy.annotations.StaticHeader;
import com.luckyframework.httpclient.proxy.handle.ExceptionFallback;
import io.github.lucklike.entity.request.User;
import io.github.lucklike.entity.response.Result;
import io.github.lucklike.httpclient.annotation.HttpClientComponent;
import io.github.lucklike.luckyclient.api.server.fallbak.AnnUserApiFallBack;

/**
 * @author fukang
 * @version 1.0.0
 * @date 2024/7/9 01:45
 */
@ExceptionFallback(fallback = @ObjectGenerate(msg = "annUserApiFallBack"))
@HttpClientComponent
public interface AnnUserApi extends LuckyServerApi {

    @StaticHeader({"H1: 123", "H1: 345"})
    @Post("/user/post")
    User postUser(@JsonBody User user);

    @Get("/user/get")
    Result<User> getUser();
}
