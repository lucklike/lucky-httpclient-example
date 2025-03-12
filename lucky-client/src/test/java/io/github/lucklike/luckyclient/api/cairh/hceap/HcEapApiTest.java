package io.github.lucklike.luckyclient.api.cairh.hceap;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class HcEapApiTest {

    @Resource
    private HcEapApi api;

    @Test
    void getSignature() {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("url", "/hseap/v1/fileUpload.json");
        paramMap.put("method", "POST");

        String signature = api.getSignature(paramMap);
        System.out.println(signature);
    }

    @Test
    void uploadFile() {
        api.uploadFile("test");
    }
}