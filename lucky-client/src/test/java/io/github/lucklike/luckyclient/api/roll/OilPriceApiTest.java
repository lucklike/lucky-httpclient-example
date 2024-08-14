package io.github.lucklike.luckyclient.api.roll;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class OilPriceApiTest {

    @Resource
    private OilPriceApi api;

    @Test
    void query() {
        System.out.println(api.query("湖北"));
    }
}