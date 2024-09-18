package io.github.lucklike.luckyclient.api.server.configapi;

import com.luckyframework.httpclient.core.meta.Response;
import com.luckyframework.httpclient.proxy.mock.MockResponse;
import io.github.lucklike.entity.request.User;
import io.github.lucklike.entity.response.Result;
import io.github.lucklike.httpclient.configapi.ResourceHttpClient;

/**
 * @author fukang
 * @version 1.0.0
 * @date 2024/8/3 04:01
 */
@ResourceHttpClient
public interface UserApi {

    User postUser(User user);

    Result<User> getUser();

    static Response getUserMock() {
        User user = new User();
        user.setId(11);
        user.setName("Lucky");
        user.setEmail("lucky@gmail.com");
        user.setPassword("lucky");
        return MockResponse.create().json(Result.success(user));
    }
}
