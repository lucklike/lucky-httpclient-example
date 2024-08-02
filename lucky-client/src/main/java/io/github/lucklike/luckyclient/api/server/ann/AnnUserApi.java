package io.github.lucklike.luckyclient.api.server.ann;

import com.luckyframework.httpclient.proxy.annotations.Get;
import com.luckyframework.httpclient.proxy.annotations.JsonBody;
import com.luckyframework.httpclient.proxy.annotations.Post;
import com.luckyframework.httpclient.proxy.annotations.RespSelect;
import com.luckyframework.httpclient.proxy.annotations.SSL;
import io.github.lucklike.entity.request.User;
import io.github.lucklike.entity.response.Result;
import io.github.lucklike.httpclient.annotation.HttpClient;

/**
 * @author fukang
 * @version 1.0.0
 * @date 2024/7/9 01:45
 */
@HttpClient("${lucky-server}/user")
public interface AnnUserApi {

    @RespSelect("#{$body$.data}")
    @Post("post")
    User postUser(@JsonBody User user);

    @SSL
    @Get("get")
    Result<User> getUser();
}
