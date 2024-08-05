package io.github.lucklike.luckyclient.api.ssl.configapi;

import io.github.lucklike.entity.request.User;
import io.github.lucklike.entity.response.Result;
import io.github.lucklike.httpclient.annotation.HttpClientComponent;

@HttpClientComponent
public interface SSLUserApi extends BaseSSLConfigApi {

    User postUser(User user);

    Result<User> getUser();

}
