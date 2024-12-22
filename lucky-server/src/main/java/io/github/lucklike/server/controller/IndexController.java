package io.github.lucklike.server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author fukang
 * @version 1.0.0
 * @date 2024/12/21 23:10
 */
@Controller
public class IndexController {

    @GetMapping("index")
    public String index() {
        return "index";
    }

    @GetMapping("index2")
    public String index2() {
        return "index2";
    }
}
