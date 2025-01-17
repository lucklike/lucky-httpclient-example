package io.github.lucklike.luckyclient.api.server;

import com.luckyframework.async.EnhanceFuture;
import com.luckyframework.async.EnhanceFutureFactory;
import com.luckyframework.common.DateUtils;
import com.luckyframework.io.RepeatableReadFileInputStream;
import com.luckyframework.io.RepeatableReadStreamUtil;
import com.luckyframework.threadpool.ThreadPoolFactory;
import com.luckyframework.threadpool.ThreadPoolParam;
import io.github.lucklike.entity.request.User;
import io.github.lucklike.entity.response.Result;
import io.github.lucklike.luckyclient.api.server.ann.AnnUserApi;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;

import static com.luckyframework.httpclient.proxy.CommonFunctions.resource;

/**
 * @author fukang
 * @version 1.0.0
 * @date 2024/7/9 01:47
 */
@Slf4j
@SpringBootTest
public class AnnUserApiTest {

    @Resource
    private AnnUserApi api;

    @Test
    void postUserTest() {
        User user = new User();
        user.setId(1);
        user.setName("Jack的猫");
        user.setPassword("PA$$W0RD");
        user.setPhone("17363312985");
        user.setEmail("1814375626@qq.com");
        User userResult = api.postUser(user);

        System.out.println(userResult);
    }

    @Test
    void getUserTest() {
        Result<User> user = api.getUser();
        System.out.println(user);
//        User user1 = api.postUser(user.getData());
//
//        System.out.println(user1);
    }

    @Test
    void fuseTest() throws InterruptedException {
        System.out.println(DateUtils.time());
        while (true) {
            api.getUser();
        }
    }

    @Test
    void errorTest() {
        api.error();
    }

    @Test
    void postTest() {
        api.postTest("Jack", 12);
    }


    @Test
    void postTest2() {
        api.postTest2(333, "aaaaaa");
    }

    @Test
    void postTest3() {
        api.postTest3(333, "aaaaaa");
    }

    @Test
    void testUpload() throws IOException {
        Map<String, InputStream> map = new HashMap<>();
        map.put("books.json", resource("classpath:books.json").getInputStream());
        map.put("application.yml", resource("classpath:application.yml").getInputStream());

        String hello = api.upload("Hello", map);
        System.out.println(hello);
    }


    @Test
    void fuseTest2() {
        ThreadPoolParam param = new ThreadPoolParam();
        ThreadPoolExecutor threadPool = ThreadPoolFactory.createThreadPool(param);
        EnhanceFutureFactory futureFactory = new EnhanceFutureFactory(threadPool);
        EnhanceFuture<Result<User>> getUserFuture = futureFactory.create();
        EnhanceFuture<User> objectEnhanceFuture = futureFactory.create();
        Result<User> user = api.getUser();
        for (int i = 0; i < 10000; i++) {
            getUserFuture.addAsyncTask(() -> api.getUser());
            objectEnhanceFuture.addAsyncTask(() -> api.postUser(user.getData()));
        }
        for (Result<User> result : getUserFuture.getResults()) {
            System.out.println(result);
        }
        for (User result : objectEnhanceFuture.getResults()) {
            System.out.println(result);
        }
    }
}
