package io.github.lucklike.discovery.eureka.api;

import com.luckyframework.common.RunnableActuator;
import com.luckyframework.common.StopWatch;
import io.github.lucklike.common.api.UserApi;
import io.github.lucklike.entity.request.User;
import io.github.lucklike.entity.response.Result;
import io.github.lucklike.httpclient.injection.HttpReference;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author fukang
 * @version 1.0.0
 * @date 2025/3/13 00:18
 */
@SpringBootTest
class UserApiTest {

    @HttpReference
    private UserApi userApi;

    @Test
    void getUser() {
        StopWatch stopWatch = new StopWatch();
        RunnableActuator.repeat(10, i -> {
            stopWatch.start( "task-" + i);
            Result<User> user = userApi.getUser(i + "");
            System.out.println(user);
            stopWatch.stopLast();
        });

        stopWatch.stopWatch();
        System.out.println(stopWatch.prettyPrintFormat());

    }
}