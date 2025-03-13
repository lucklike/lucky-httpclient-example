package io.github.lucklike.common.controller;

import io.github.lucklike.common.api.UserApi;
import io.github.lucklike.entity.request.User;
import io.github.lucklike.entity.response.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fukang
 * @version 1.0.0
 * @date 2025/3/13 22:09
 */
@RequestMapping("/user")
@RestController
public class UserController implements UserApi {

    @Override
    @GetMapping("get/{id}")
    public Result<User> getUser(@PathVariable("id") String id) {
        User user = new User();
        user.setId(1);
        user.setName("lucklike");
        user.setAge(18);
        user.setEmail("lucklike@lucklike.io");
        user.setPassword("lucklike");
        user.setPhone("1233422333");
        System.out.println("getUser --> " + id);
        return Result.success(user);
    }
}
