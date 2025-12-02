package io.github.lucklike.luckyclient.api.mock.type;

import com.luckyframework.httpclient.core.meta.Response;
import com.luckyframework.httpclient.proxy.annotations.Get;
import com.luckyframework.httpclient.proxy.annotations.RespConvert;
import com.luckyframework.httpclient.proxy.context.MethodContext;
import com.luckyframework.httpclient.proxy.mock.Mock;
import com.luckyframework.httpclient.proxy.mock.MockResponse;
import com.luckyframework.httpclient.proxy.spel.Rar;
import io.github.lucklike.httpclient.discovery.HttpClient;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

import static com.luckyframework.httpclient.proxy.function.CommonFunctions.typeOf;


/**
 * @author fukang
 * @version 1.0.0
 * @date 2025/11/4 01:29
 */
//@RespConvert(result = "#{$body$.data}")
@RespConvert(result = "#{$body$.data}", metaTypeFunc = "autoTypeConvert")
@HttpClient(url = "http://localhost:8080/", path = "user")
public interface TypeConvertApi {

    @Mock
    @Get("get/#{id}")
    User getUserById(Integer id);

    static Response getUserById$Mock(@Rar("id") Integer id) {
        BaseResult<User> result = new BaseResult<>();
        result.setMessage("OK");
        result.setCode(200);
        User user = new User();
        user.setId(id);
        user.setName("Lucky User");
        user.setAge(18);
        user.setEmail("luckyuser@luckyclient.com");
        user.setPhone("123456789");
        result.setData(user);
        return MockResponse.create().status(200).json(result);
    }

    @Mock
    @Get("/")
    List<User> getUsers();

    static Response getUsers$Mock() {
        BaseResult<List<User>> result = new BaseResult<>();
        result.setMessage("OK");
        result.setCode(200);
        User user1 = new User();
        user1.setId(1);
        user1.setName("Lucky User");
        user1.setAge(18);
        user1.setEmail("luckyuser@luckyclient.com");
        user1.setPhone("123456789");

        User user2 = new User();
        user2.setId(2);
        user2.setName("Jack User");
        user2.setAge(21);
        user2.setEmail("jackuser@luckyclient.com");
        user2.setPhone("987654321");
        result.setData(Arrays.asList(user1, user2));
        return MockResponse.create().status(200).json(result);
    }




    static Type autoTypeConvert(MethodContext mc) {
        return typeOf(BaseResult.class, mc.getMethodConvertReturnResolvableType()).getType();
    }
}
