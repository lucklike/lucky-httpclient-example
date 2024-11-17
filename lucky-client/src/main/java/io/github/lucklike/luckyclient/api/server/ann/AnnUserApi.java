package io.github.lucklike.luckyclient.api.server.ann;

import com.luckyframework.httpclient.generalapi.describe.Describe;
import com.luckyframework.httpclient.proxy.annotations.Get;
import com.luckyframework.httpclient.proxy.annotations.JsonBody;
import com.luckyframework.httpclient.proxy.annotations.Post;
import io.github.lucklike.entity.request.User;
import io.github.lucklike.entity.response.Result;
import io.github.lucklike.httpclient.annotation.HttpClientComponent;

/**
 * @author fukang
 * @version 1.0.0
 * @date 2024/7/9 01:45
 */

@HttpClientComponent
@Describe(author = "付康", version = "1.0.0", contactWay = "17363312985")
public interface AnnUserApi extends LuckyServerApi {

    @Describe("注册用户")
    @Post("/user/post")
    User postUser(@JsonBody User user);

    @Describe("获取用户信息")
    @Get("/user/get")
    Result<User> getUser();

    @Describe("异常测试")
    @Get("/user/error?d=123")
    void error();

}
