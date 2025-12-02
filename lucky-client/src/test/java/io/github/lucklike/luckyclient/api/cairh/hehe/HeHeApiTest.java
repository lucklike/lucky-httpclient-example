package io.github.lucklike.luckyclient.api.cairh.hehe;

import com.luckyframework.common.ConfigurationMap;
import com.luckyframework.reflect.AnnotationUtils;
import io.github.lucklike.luckyclient.api.cairh.hehe.resp.BusinessLicenseResponse;
import io.github.lucklike.luckyclient.api.cairh.hehe.resp.GATResidenceResponse;
import io.github.lucklike.luckyclient.api.cairh.hehe.resp.HeheIdCardResponse;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.List;

import static com.luckyframework.httpclient.proxy.function.CommonFunctions.resource;
import static com.luckyframework.httpclient.proxy.function.SerializationFunctions.base64;


@SpringBootTest
class HeHeApiTest {

    @Resource
    private HeHeApi heHeApi;

    @Test
    void ocrIdCard() {
        HeheIdCardResponse response = heHeApi.ocrIdCard("file:D:/id/card/20240712093345.jpg");
        System.out.println(response);
    }

    @Test
    void testOcrBusinessLicense() {
        BusinessLicenseResponse response = heHeApi.ocrBusinessLicense("file:D:/id/card/R-C.jpg");
        System.out.println(response);
    }

    @Test
    void testOcrGATResidence() {
        GATResidenceResponse response = heHeApi.ocrGATResidence("file:D:/id/card/GAT-1.jpg");
        System.out.println(response);
    }

    @Test
    void testOcrPermanentResident() {
        ConfigurationMap response = heHeApi.ocrPermanentResident("file:D:/id/card/WGR-YJZ-1.png");
        System.out.println(response);
    }

//    @Test
//    void ocrFaceCompare() throws IOException {
//        FaceCompareReq req = new FaceCompareReq();
//        req.setImgA(_res64("file:D:/id/20240913_165323.mp4"));
//        req.setImgB(_res64("file:D:/id/20240913_165323.mp4"));
//        ConfigurationMap response = heHeApi.ocrFaceCompare(req);
//        System.out.println(response);
//    }


    private String _res64(String path) throws IOException {
        return base64(resource(path));
    }

    public static void main(String[] args) {
        List<Annotation> annotationList = AnnotationUtils.getCombinationAnnotations(HeheBaseApi.class);
        annotationList.forEach(System.out::println);
    }
}