package io.github.lucklike.luckyclient.api.cairh.comp;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FundSumaryPublishApiTest {

    @Resource
    private FundSumaryPublishApi api;

    @Test
    void publish() {
        api.publish("300004", "file:D:/cairh/test/*.pdf");
    }
}