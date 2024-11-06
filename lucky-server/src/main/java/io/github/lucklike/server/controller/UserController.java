package io.github.lucklike.server.controller;

import cn.hutool.core.lang.id.NanoId;
import cn.hutool.core.util.RandomUtil;
import io.github.lucklike.entity.request.User;
import io.github.lucklike.entity.response.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author fukang
 * @version 1.0.0
 * @date 2024/7/9 01:43
 */
@RestController
@RequestMapping("user")
public class UserController {

    private AtomicLong postCount = new AtomicLong(0);
    private AtomicLong getCount = new AtomicLong(0);

    @PostMapping("post")
    public Result<User> postUser(@RequestBody User user){
        System.out.println(postCount.getAndAdd(1) + "[POST]==>" +user);
        return Result.success(user);
    }

    @GetMapping("get")
    public Result<User> getUser() throws InterruptedException {
        int s = RandomUtil.randomInt(1000, 3000);
        System.out.println(getCount.getAndAdd(1) + "[GET]==>" + s);
        Thread.sleep(s);
        User user = new User();
        user.setId((int)(10000 * Math.random()));
        user.setName(NanoId.randomNanoId());
        user.setPhone("1234567890");
        user.setEmail("1234567890@qq.com");
        user.setPassword("**********");
        return Result.success(user);
    }
}
