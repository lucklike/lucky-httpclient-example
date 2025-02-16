package io.github.lucklike.luckyclient.api.cairh.ect888;

import io.github.lucklike.luckyclient.api.cairh.ect888.ann.Ect888Client;
import io.github.lucklike.luckyclient.api.cairh.ect888.req._2000348Req;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class Ect888ApiTest {

    @Resource
    private Ect888Api ect888Api;

    @Test
    void call() {
        _2000348Req req = new _2000348Req();
        req.setId("421022199609115413");
        req.setName("付康");
        String resp = ect888Api.call(req);
        System.out.println(resp);
    }
}