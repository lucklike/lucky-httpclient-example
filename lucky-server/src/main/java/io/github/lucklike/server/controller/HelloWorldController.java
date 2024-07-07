package io.github.lucklike.server.controller;

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
    public String hello() {
        return "Hello World!";
    }

    @GetMapping("exception")
    public String exception() {
        int i = 1 / 0;
        return "Error";
    }
}
