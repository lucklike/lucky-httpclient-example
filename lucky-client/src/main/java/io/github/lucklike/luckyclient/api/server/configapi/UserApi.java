package io.github.lucklike.luckyclient.api.server.configapi;

import io.github.lucklike.entity.request.User;
import io.github.lucklike.entity.response.Result;
import io.github.lucklike.httpclient.configapi.LocalConfigHttpClient;

/**
 * @author fukang
 * @version 1.0.0
 * @date 2024/8/3 04:01
 */
@LocalConfigHttpClient
public interface UserApi {

    User postUser(User user);

    Result<User> getUser();
}
