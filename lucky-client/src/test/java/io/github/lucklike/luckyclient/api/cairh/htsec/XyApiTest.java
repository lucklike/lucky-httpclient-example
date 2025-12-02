package io.github.lucklike.luckyclient.api.cairh.htsec;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.FileCopyUtils;

import javax.annotation.Resource;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static com.luckyframework.httpclient.proxy.function.SerializationFunctions.json;


@SpringBootTest
class XyApiTest {

    @Resource
    private XyApi api;

    @Test
    void getXyData() throws Exception {

        List<Object> xyData = api.getXyData();
        System.out.println(xyData);

        FileCopyUtils.copy(json(xyData).getBytes(StandardCharsets.UTF_8), new File("D:/test/ele/bank-021804.json"));
    }
}