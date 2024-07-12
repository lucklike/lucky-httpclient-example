package io.github.lucklike.server.controller;

import io.github.lucklike.entity.request.User;
import io.github.lucklike.entity.response.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fukang
 * @version 1.0.0
 * @date 2024/7/9 01:43
 */
@RestController
@RequestMapping("user")
public class UserController {

    @PostMapping("post")
    public Result<User> postUser(@RequestBody User user){
        System.out.println(user);
        return Result.success(user);
    }
}