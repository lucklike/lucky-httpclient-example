package io.github.lucklike.luckyclient.api.server.ann;

import com.luckyframework.httpclient.proxy.annotations.Get;
import com.luckyframework.httpclient.proxy.annotations.JsonBody;
import com.luckyframework.httpclient.proxy.annotations.Post;
import com.luckyframework.httpclient.proxy.annotations.RespConvert;
import io.github.lucklike.entity.request.User;
import io.github.lucklike.entity.response.Result;
import io.github.lucklike.httpclient.annotation.HttpClientComponent;

/**
 * @author fukang
 * @version 1.0.0
 * @date 2024/7/9 01:45
 */
@HttpClientComponent
public interface AnnUserApi extends LuckyServerApi {

    @RespConvert("#{$body$.data}")
    @Post("/user/post")
    User postUser(@JsonBody User user);

    @Get("/user/get")
    Result<User> getUser();
}
