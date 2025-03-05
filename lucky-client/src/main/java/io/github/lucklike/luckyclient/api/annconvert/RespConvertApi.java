package io.github.lucklike.luckyclient.api.annconvert;

import com.luckyframework.httpclient.proxy.annotations.Async;
import com.luckyframework.httpclient.proxy.annotations.Get;
import com.luckyframework.httpclient.proxy.annotations.RespConvert;
import com.luckyframework.httpclient.proxy.handle.ResultHandler;
import com.luckyframework.httpclient.proxy.mock.Mock;
import com.luckyframework.httpclient.proxy.mock.MockResponse;
import io.github.lucklike.entity.request.User;
import io.github.lucklike.entity.response.Result;
import io.github.lucklike.httpclient.annotation.HttpClient;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Mock(mockFunc = "userList")
@HttpClient("http://localhost:8864/user/")
public interface RespConvertApi {

    @RespConvert("#{$body$.data}")
    @Get("/user/list")
    List<User> getUsers();

    @RespConvert("#{$body$.data.?[#this.age >= 18]}")
    @Get("/user/list")
    List<User> getAdultUsers();

    @Async
    @RespConvert("#{$body$.data.?[#this.age >= 18]}")
    @Get("/user/list")
    void adultUsersHandler(ResultHandler<Optional<List<User>>> resultHandler);

    @RespConvert("#{$body$.data.![#this.id]}")
    @Get("/user/list")
    List<Integer> getAllUseId();

    @RespConvert
    @Get("/user/list")
    void queryAndSaveToDB();

    @GsonDecoder("#{$gdata$.data.^[#this.id eq #root.uid]}")
    @Get("/user/list")
    User gsonDecoderFindById(Integer uid);


    //----------------------------------------------------------
    //                      Mock method
    //----------------------------------------------------------


    static MockResponse userList() {
        User user1 = new User();
        user1.setId(1);
        user1.setName("User-1");
        user1.setEmail("User-1@qq.com");
        user1.setAge(18);

        User user2 = new User();
        user2.setId(2);
        user2.setName("User-2");
        user2.setEmail("User-2@qq.com");
        user2.setAge(17);

        User user3 = new User();
        user3.setId(3);
        user3.setName("User-3");
        user3.setEmail("User-3@qq.com");
        user3.setAge(16);

        List<User> users = Arrays.asList(user1, user2, user3);
        return MockResponse.create().status(200).json(Result.success(users));
    }
}
