package io.github.lucklike.luckyclient.api.cairh.hehe;

import com.luckyframework.common.ConfigurationMap;
import com.luckyframework.reflect.AnnotationUtils;
import io.github.lucklike.luckyclient.api.cairh.hehe.req.FaceCompareReq;
import io.github.lucklike.luckyclient.api.cairh.hehe.resp.HeheIdCardResponse;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.List;

import static com.luckyframework.httpclient.proxy.CommonFunctions.base64;
import static com.luckyframework.httpclient.proxy.CommonFunctions.resource;

@SpringBootTest
class HeHeApiTest {

    @Resource
    private HeHeApi heHeApi;

    @Test
    void ocrIdCard() {
        HeheIdCardResponse response = heHeApi.ocrIdCard("file:/Users/fukang/Pictures/1234.jpg");
        System.out.println(response);
    }

    @Test
    void ocrFaceCompare() throws IOException {
        FaceCompareReq req = new FaceCompareReq();
        req.setImgA(_res64("file:/Users/fukang/Pictures/1234.jpg"));
        req.setImgB(_res64("file:/Users/fukang/Pictures/1234.jpg"));
        ConfigurationMap response = heHeApi.ocrFaceCompare(req);
        System.out.println(response);
    }


    private String _res64(String path) throws IOException {
        return base64(resource(path));
    }

    public static void main(String[] args) {
        List<Annotation> annotationList = AnnotationUtils.getCombinationAnnotations(HeheBaseApi.class);
        annotationList.forEach(System.out::println);
    }
}