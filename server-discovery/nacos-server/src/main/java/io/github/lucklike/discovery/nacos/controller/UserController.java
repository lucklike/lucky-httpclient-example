package io.github.lucklike.discovery.nacos.controller;

import io.github.lucklike.entity.request.User;
import io.github.lucklike.entity.response.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fukang
 * @version 1.0.0
 * @date 2025/3/12 23:00
 */
@RequestMapping("/user")
@RestController
public class UserController {

    @GetMapping("get")
    public Result<User> getUser() {
        User user = new User();
        user.setId(1);
        user.setName("lucklike");
        user.setAge(18);
        user.setEmail("lucklike@lucklike.io");
        user.setPassword("lucklike");
        user.setPhone("1233422333");
        return Result.success(user);
    }
}
