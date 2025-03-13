package io.github.lucklike.discovery.consul.api;

import com.luckyframework.httpclient.proxy.annotations.Get;
import io.github.lucklike.entity.request.User;
import io.github.lucklike.entity.response.Result;
import io.github.lucklike.httpclient.discovery.HttpClient;

/**
 * @author fukang
 * @version 1.0.0
 * @date 2025/3/13 00:16
 */
@HttpClient(service = "lucky-consul-server")
public interface UserApi {

    @Get("/user/get")
    Result<User> getUser();
}
