package io.github.lucklike.luckyclient.api.mock;

import com.luckyframework.httpclient.proxy.function.ResourceFunctions;
import com.luckyframework.spel.SimpleSpelBean;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import io.github.lucklike.luckyclient.api.mock.type.User;

import java.util.List;

import static com.luckyframework.httpclient.proxy.function.CommonFunctions.typeOf;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author fukang
 * @version 1.0.0
 * @date 2025/11/21 01:33
 */
@SpringBootTest
class JsonMockApiTest {

    @Resource
    private JsonMockApi api;

    @Test
    void envJson() {
        User user = new User();
        user.setId(5);
        user.setName("Jack");
        api.envJson(user);
    }

    @Test
    void envJsonArray() {
        User user = new User();
        user.setId(5);
        user.setName("Jack");
        api.envJsonArray(user);
    }

    @Test
    void resJson() {
        User user = new User();
        user.setId(5);
        user.setName("Jack");
        api.resJson(user);
    }

    @Test
    void resJsonArray() {
        User user = new User();
        user.setId(5);
        user.setName("Jack");
        api.resJsonArray(user);
    }

    @Test
    void resJsonArrayProp() {
        User user = new User();
        user.setId(5);
        user.setName("Jack");
        api.resJsonArrayProp(user);
    }

    @Test
    void readSpelBean() {
        SimpleSpelBean<?> simpleSpelBean = ResourceFunctions.readSpelBean("classpath:/param-temp/users.xml");
        System.out.println(simpleSpelBean);
    }

    @Test
    void readAsBean() {
        List<User> users = ResourceFunctions.readAsBean("classpath:/param-temp/users.yaml", typeOf(List.class, User.class));
        System.out.println(users);
    }
}