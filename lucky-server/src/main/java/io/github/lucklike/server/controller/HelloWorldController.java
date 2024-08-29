package io.github.lucklike.server.controller;

import io.github.lucklike.entity.response.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Hello World
 *
 * @author fukang
 * @version 1.0.0
 * @date 2024/7/7 03:09
 */
@RestController
public class HelloWorldController {

    @GetMapping("hello")
    public Result<String> hello() {
        return Result.success("Hello World!");
    }

    @GetMapping("exception")
    public Result<String> exception() {
        int i = 1 / 0;
        return Result.success("Error");
    }
}
