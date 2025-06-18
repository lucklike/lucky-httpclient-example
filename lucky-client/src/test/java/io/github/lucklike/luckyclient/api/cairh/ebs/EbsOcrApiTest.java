package io.github.lucklike.luckyclient.api.cairh.ebs;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EbsOcrApiTest {

    @Resource
    EbsOcrApi ebsOcrApi;

    @Test
    void idCardParse() {
        String result = ebsOcrApi.idCardParse("file:D:/id/card/20240712093345.jpg");
        System.out.println(result);
    }
}