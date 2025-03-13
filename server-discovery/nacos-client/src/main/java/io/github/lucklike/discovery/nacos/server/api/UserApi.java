package io.github.lucklike.discovery.nacos.server.api;

import com.luckyframework.httpclient.proxy.annotations.Get;
import io.github.lucklike.entity.request.User;
import io.github.lucklike.entity.response.Result;
import io.github.lucklike.httpclient.discovery.HttpClient;

/**
 * @author fukang
 * @version 1.0.0
 * @date 2025/3/12 23:06
 */
@HttpClient(service = "lucky-nacos-server", path = "user")
public interface UserApi {

    @Get("get")
    Result<User> getUser();
}
