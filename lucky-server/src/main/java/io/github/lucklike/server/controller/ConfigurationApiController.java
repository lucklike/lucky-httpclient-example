package io.github.lucklike.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/config/")
public class ConfigurationApiController {

    @Autowired
    private ResourceLoader resourceLoader;


    @GetMapping("/api/{name}")
    public String getConfig(@PathVariable("name") String name) throws IOException {
        String resourceLocation = "classpath:config-api/" + name;
        Resource resource = resourceLoader.getResource(resourceLocation);
        if (resource.isFile() && resource.exists()) {
            return FileCopyUtils.copyToString(new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8));
        }
        throw new FileNotFoundException("Resource not found: " + name);
    }
}
