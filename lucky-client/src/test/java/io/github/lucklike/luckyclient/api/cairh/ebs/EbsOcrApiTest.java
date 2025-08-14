package io.github.lucklike.luckyclient.api.cairh.ebs;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Set;

import static com.luckyframework.httpclient.proxy.CommonFunctions.typeOf;
import static io.github.lucklike.httpclient.function.BeanFunction.env;

@SpringBootTest
class EbsOcrApiTest {

    @Resource
    EbsOcrApi ebsOcrApi;

    @Test
    void idCardParse() {
        String result = ebsOcrApi.idCardParse("file:D:/id/card/20240712093345.jpg");
        System.out.println(result);
    }

    @Test
    void bindTest() {
        Set<Map<String, Object>> env = env("lucky.http-client.ssl.key-stores", typeOf(Set.class, typeOf(Map.class, "java.lang.String", Object.class)));
        System.out.println(env);
    }
}