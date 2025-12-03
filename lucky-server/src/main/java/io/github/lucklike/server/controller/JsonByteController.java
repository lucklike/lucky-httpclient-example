package io.github.lucklike.server.controller;

import io.github.lucklike.entity.request.bytes.ByteJson;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import static com.luckyframework.httpclient.proxy.function.ResourceFunctions.resource;


/**
 * @author fukang
 * @version 1.0.0
 * @date 2025/1/15 01:11
 */
@RestController
@RequestMapping("byte")
public class JsonByteController {

    @GetMapping("json")
    public ByteJson byteJson() throws IOException {
        ByteJson byteJson = new ByteJson();
        byteJson.setName("json");
        byteJson.setData(FileCopyUtils.copyToByteArray(resource("classpath:application.yml").getInputStream()));
        return byteJson;
    }
}
