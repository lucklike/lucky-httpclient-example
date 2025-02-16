package io.github.lucklike.luckyclient.api.cairh.ttd;

import io.github.lucklike.luckyclient.api.cairh.ttd.req.Page;
import io.github.lucklike.luckyclient.api.cairh.ttd.resp.Product;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class TTDApiTest {

    @Resource
    private TTDApi ttdApi;

    @Test
    void productList() {
        List<Product> productList = ttdApi.productList(Page.of(1, 10));
        System.out.println(productList);
    }

    @Test
    void getAccessToken() {
        System.out.println(ttdApi.getAccessToken());
    }
}