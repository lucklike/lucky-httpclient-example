package io.github.lucklike.luckyclient.api.kuaitong;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class KuaiTongApiTest {

    @Resource
    private KuaiTongApi kuaiTongApi;

    @Test
    void ocrTest() {
        String path = "file:/Users/fukang/Desktop/test/ca/seal/157308489251687784.jpg";
        IdentityInfo identityInfo = kuaiTongApi.identityCardOcr(path);
        System.out.println(identityInfo);
    }
}
