package io.github.lucklike.discovery.consul.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @RequestMapping("/actuator/health")
    public String health() {
        return "OK";
    }
}
