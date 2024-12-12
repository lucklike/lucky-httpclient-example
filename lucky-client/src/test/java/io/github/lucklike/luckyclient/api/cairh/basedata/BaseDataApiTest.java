package io.github.lucklike.luckyclient.api.cairh.basedata;

import com.luckyframework.async.EnhanceFuture;
import com.luckyframework.async.EnhanceFutureFactory;
import io.github.lucklike.luckyclient.api.cairh.basedata.resp.OperInfoQry;
import io.github.lucklike.luckyclient.api.cairh.basedata.resp.RareWordResponse;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class BaseDataApiTest {

    @Resource
    BaseDataApi api;

    @Test
    void testQueryOperInfo() {
        OperInfoQry operInfoQry = api.queryOperInfo("1163");
        System.out.println(operInfoQry);
    }

    @Test
    void testGetOneRareWord() {
        RareWordResponse rareWord = api.getOneRareWord("20241121589310176240");
        System.out.println(rareWord);
    }

    @Test
    void testGetOperName() {
//        String operName = api.getOperName("999");
//        System.out.println(operName);
    }

    @Test
    void testGetOperNameAsync() {
        EnhanceFutureFactory futureFactory = new EnhanceFutureFactory();
        EnhanceFuture<String> enhanceFuture = futureFactory.create();
        enhanceFuture.addFuture(api.getOneRareWordSource("20241121589310176240"));
        enhanceFuture.addFuture(api.getOneRareWordSource("20241121589310176231"));
        System.out.println(enhanceFuture.getResultMap());
    }
}