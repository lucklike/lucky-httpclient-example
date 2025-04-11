package io.github.lucklike.luckyclient.api.cairh.htsec;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.Map;

@SpringBootTest
class HtqhOcrApiTest {

    @Resource
    private HtqhOcrApi api;

    @Test
    void idCard() {
        Map<String, Object> map = api.idCard("file:D:/id/card/20240712093345.jpg");
        System.out.println(map);
    }

    @Test
    void bankCard() {
        Map<String, Object> map = api.bankCard("file:D:/id/card/fk-hzyh (1).jpg");
        System.out.println(map);
    }

    @Test
    void bankCardInfo() {
        String bankCardNo = api.bankCardInfo("file:D:/id/card/fk-hzyh (1).jpg");
        System.out.println(bankCardNo);
    }

    @Test
    void idCardInfo() {
        String idCardInfo = api.idCardInfo("file:D:/id/card/20240719110322.jpg");
        System.out.println(idCardInfo);
    }
}