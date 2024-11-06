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
        User user1 = new User();
        user1.setId(3333);
        user1.setEmail("FallBack@example.com");
        user1.setPhone("123544454");
        return user1;
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
