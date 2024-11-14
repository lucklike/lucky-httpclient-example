package io.github.lucklike.luckyclient.api.cairh.hehe;

import com.luckyframework.common.ConfigurationMap;
import com.luckyframework.httpclient.proxy.CommonFunctions;
import io.github.lucklike.luckyclient.api.cairh.hehe.req.FaceCompareReq;
import io.github.lucklike.luckyclient.api.cairh.hehe.resp.HeheIdCardResponse;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.io.IOException;

import static com.luckyframework.httpclient.proxy.CommonFunctions.base64;
import static com.luckyframework.httpclient.proxy.CommonFunctions.resource;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class HeHeApiTest {

    @Resource
    private HeHeApi heHeApi;

    @Test
    void ocrIdCard() {
        HeheIdCardResponse response = heHeApi.ocrIdCard("file:D:/id/card/20240719110322.jpg");
        System.out.println(response);
    }

    @Test
    void ocrFaceCompare() throws IOException {
        FaceCompareReq req = new FaceCompareReq();
        req.setImgA(_res64("file:D:/id/card/wbq-1.jpg"));
        req.setImgB(_res64("file:D:/id/card/wyz-1.jpg"));
        ConfigurationMap response = heHeApi.ocrFaceCompare(req);
        System.out.println(response);
    }


    private String _res64(String path) throws IOException {
        return base64(resource(path));
    }
}