package io.github.lucklike.luckyclient.api;

import com.luckyframework.httpclient.proxy.annotations.JsonBody;
import com.luckyframework.httpclient.proxy.annotations.Post;
import com.luckyframework.httpclient.proxy.annotations.RespSelect;
import io.github.lucklike.entity.request.User;
import io.github.lucklike.entity.response.Result;
import io.github.lucklike.httpclient.annotation.HttpClient;

/**
 * @author fukang
 * @version 1.0.0
 * @date 2024/7/9 01:45
 */
@HttpClient("${lucky-server}/user")
public interface UserApi {

    @RespSelect("#{$body$.data}")
    @Post("post")
    User postUser(@JsonBody User user);

}
