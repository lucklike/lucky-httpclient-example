package io.github.lucklike.luckyclient.api.restTepmlate;

import io.github.lucklike.entity.request.User;
import io.github.lucklike.entity.response.Result;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RestTemplateDemoTest {

    @Resource
    private RestTemplateDemo restTemplateDemo;

    @Test
    void fileUpload() throws IOException {
        Result<String> stringResult = restTemplateDemo.fileUpload();
        System.out.println(stringResult);
    }

    @Test
    void fileUpload2() throws IOException {
        Result<User> stringResult = restTemplateDemo.postUser();
        System.out.println(stringResult);
    }
}