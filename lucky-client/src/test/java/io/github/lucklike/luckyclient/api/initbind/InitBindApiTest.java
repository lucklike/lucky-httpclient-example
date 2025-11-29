package io.github.lucklike.luckyclient.api.initbind;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * {@link io.github.lucklike.httpclient.convert.InitBind} Test
 * @author fukang
 * @version 1.0.0
 * @date 2025/9/28 19:41
 */
@SpringBootTest
class InitBindApiTest {

    @Resource
    private InitBindApi initBindApi;

    @Test
    void query() {
        MyRequest request = new MyRequest();
//        request.setFunCode("31");
//        request.setAppId("app-11");

        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("text", "text");
        queryMap.put("q", "qqqq");
        String query = initBindApi.query(request, queryMap);
        System.out.println(query);
    }
}