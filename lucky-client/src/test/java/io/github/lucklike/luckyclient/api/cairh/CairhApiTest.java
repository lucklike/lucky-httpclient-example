package io.github.lucklike.luckyclient.api.cairh;

import io.github.lucklike.luckyclient.api.cairh.response.QueryOperatorInfoResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

@SpringBootTest
class CairhApiTest {

    @Autowired
    private CairhApi cairhApi;

    @Test
    void queryProduct() {
        System.out.println(cairhApi.queryProduct("000086"));
    }

    @Test
    void queryOperatorInfo() {
        QueryOperatorInfoResponse response = cairhApi.queryOperatorInfo("41801");
        System.out.println(response);
    }
}