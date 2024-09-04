package io.github.lucklike.luckyclient.api.abstractapi;

import io.github.lucklike.luckyclient.api.baiduai.BaiduAI;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AbstractClassApiTest {

    @Resource
    private AbstractClassApi abstractClassApi;

    @Test
    void meagerResult() {
        Map<String, Object> meagerResult = abstractClassApi.meagerResult();
        System.out.println(meagerResult);
    }
}