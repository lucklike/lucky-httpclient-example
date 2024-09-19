package io.github.lucklike.luckyclient.api.cairh;

import com.luckyframework.common.StopWatch;
import com.luckyframework.httpclient.proxy.configapi.CommonApi;
import com.luckyframework.reflect.ClassUtils;
import io.github.lucklike.luckyclient.api.cairh.request.JournalRequest;
import io.github.lucklike.luckyclient.api.cairh.response.QueryOperatorInfoResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Arrays;
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
        StopWatch sw = new StopWatch();
        sw.start("queryJournal");
        QueryOperatorInfoResponse response = cairhApi.queryOperatorInfo("41801");
        System.out.println(response);
        sw.stopWatch();
        System.out.println(sw.prettyPrintFormat());
    }

    @Test
    void queryJournal() {
        StopWatch sw = new StopWatch();
        sw.start("queryJournal");
        JournalRequest req = new JournalRequest();
        req.setCurrent(1);
        req.setSize(600);
        req.setTable_name("syspropertyconfigjour");
        req.setStart_time("2024-06-13 00:00:00");
        req.setEnd_time("2024-09-12 23:59:59");
        Map<String, Object> diffMap = cairhApi.queryJournal(req);
        System.out.println(diffMap);
        sw.stopWatch();
        System.out.println(sw.prettyPrintFormat());
    }

    @Test
    void blackListTemplateDownload() {
        File file = cairhApi.blackListTemplateDownload();
        System.out.println(file);
    }

    @Test
    void queryExamDetail() {
        StopWatch sw = new StopWatch();
        sw.start("queryExamDetail");
        Map<String, Object> queryExamDetail = cairhApi.queryExamDetail("202407081649230130135");
        System.out.println(queryExamDetail);
        sw.stopWatch();
        System.out.println(sw.prettyPrintFormat());
    }
}