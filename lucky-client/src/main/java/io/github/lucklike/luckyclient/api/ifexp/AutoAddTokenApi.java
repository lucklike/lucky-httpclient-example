package io.github.lucklike.luckyclient.api.ifexp;

import com.luckyframework.httpclient.proxy.annotations.Get;
import com.luckyframework.httpclient.proxy.annotations.JsonBody;
import com.luckyframework.httpclient.proxy.annotations.Post;
import com.luckyframework.httpclient.proxy.annotations.StaticHeader;
import com.luckyframework.httpclient.proxy.mock.Mock;
import io.github.lucklike.entity.request.User;
import io.github.lucklike.httpclient.discovery.HttpClient;

/**
 * @if表达式测试--自动登录测试
 *
 * @author fukang
 * @version 1.0.0
 * @date 2024/9/5 02:52
 */
@StaticHeader("@if(#{$method$.getName() != 'login'}): X-Token: #{$this$.getToken()}")
@HttpClient("http://localhost:8080")
public abstract class AutoAddTokenApi {

    private String token;

    public String getToken() {
        if (token == null) {
            token = login();
        }
        return token;
    }

    @Mock(body = "Mock-Login-Token-a7b989s78cbac898eds89")
    @Get("login")
    abstract String login();

    @Mock(body = "Mock-User")
    @Get("user")
    abstract String getUser();

    @Mock(body = "ok")
    @Post("addUser")
    abstract String addUser(@JsonBody User user);
}
