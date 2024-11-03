package io.github.lucklike.luckyclient.api.server.fallbak;

import io.github.lucklike.entity.request.User;
import io.github.lucklike.entity.response.Result;
import io.github.lucklike.luckyclient.api.server.ann.AnnUserApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AnnUserApiFallBack implements AnnUserApi {

    @Override
    public User postUser(User user) {
        return user;
    }

    @Override
    public Result<User> getUser() {
        User user = new User();
        user.setId(3333);
        user.setEmail("FallBack@example.com");
        user.setPhone("123544454");
        return Result.success(user);
    }

}
