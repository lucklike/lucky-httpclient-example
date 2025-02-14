package io.github.lucklike.luckyclient.api.cairh.ttd;

import com.luckyframework.common.ConfigurationMap;
import io.github.lucklike.luckyclient.api.cairh.ttd.resp.TtdResponse;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TTDApiTest {

    @Resource
    private TTDApi ttdApi;

    @Test
    void productList() {
        TtdResponse<Map<String, Object>> ttdResp = ttdApi.productList(1, 10);
        System.out.println(ttdResp.getData().getList());
    }

    @Test
    void getAccessToken() {
        System.out.println(ttdApi.getAccessToken());
    }
}