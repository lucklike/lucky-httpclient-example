package io.github.lucklike.luckyclient.api.cairh;

import com.luckyframework.async.EnhanceFuture;
import com.luckyframework.async.EnhanceFutureFactory;
import com.luckyframework.common.ConfigurationMap;
import com.luckyframework.common.MutableMap;
import com.luckyframework.common.StopWatch;
import com.luckyframework.common.StringUtils;
import io.github.lucklike.luckyclient.api.cairh.basedata.BaseDataApi;
import io.github.lucklike.luckyclient.api.cairh.comp.CompApi;
import io.github.lucklike.luckyclient.api.cairh.comp.CompFrontApi;
import io.github.lucklike.luckyclient.api.cairh.mall.MallApi;
import io.github.lucklike.luckyclient.api.cairh.openapi.CrhOpenApi;
import io.github.lucklike.luckyclient.api.cairh.basedata.req.JournalRequest;
import io.github.lucklike.luckyclient.api.cairh.basedata.req.RareWordRequest;
import io.github.lucklike.luckyclient.api.cairh.basedata.resp.PageResponse;
import io.github.lucklike.luckyclient.api.cairh.comp.resp.QueryOperatorInfoResponse;
import io.github.lucklike.luckyclient.api.cairh.basedata.resp.RareWordResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@SpringBootTest
class CairhApiTest {

    @Resource
    private BaseDataApi baseDataApi;

    @Resource
    private CompApi compApi;

    @Resource
    private CompFrontApi compFrontApi;

    @Resource
    private CrhOpenApi crhOpenApi;

    @Autowired
    private ScriptGenerateApi scriptGenerateApi;

    @Test
    void queryProduct() {
        System.out.println(crhOpenApi.queryProduct("000086"));
    }

    @Test
    void testGetOperatorName() {
        EnhanceFutureFactory futureFactory = new EnhanceFutureFactory();
        EnhanceFuture<String> enhanceFuture = futureFactory.create();
        enhanceFuture.addAsyncTask(() -> compFrontApi.getOperatorName("1163"));
        enhanceFuture.addAsyncTask(() -> compFrontApi.getOperatorName("999"));
        System.out.println(enhanceFuture.getResultMap());
    }

    @Test
    void queryOperatorInfo() {
        StopWatch sw = new StopWatch();
        sw.start("queryJournal");
        QueryOperatorInfoResponse response = compFrontApi.queryOperatorInfo("41801");
        QueryOperatorInfoResponse response1 = compFrontApi.queryOperatorInfo("1163");
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
        req.setSize(10);
        req.setTable_name("allbranchjour");
        Map<String, Object> diffMap = baseDataApi.queryJournal(req);
        System.out.println(diffMap);
        sw.stopWatch();
        System.out.println(sw.prettyPrintFormat());
    }

    @Test
    void blackListTemplateDownload() {
        File file = baseDataApi.blackListTemplateDownload();
        System.out.println(file);
    }

    @Test
    void queryExamDetail() {
        StopWatch sw = new StopWatch();
        sw.start("queryExamDetail-1");
        Map<String, Object> queryExamDetail = compApi.queryExamDetail("202407081649230130135");
        sw.stopLast();
        sw.start("queryExamDetail-2");
        Map<String, Object> queryExamDetail2 = compApi.queryExamDetail("202407081649230130135");
        System.out.println(queryExamDetail);
        sw.stopWatch();
        System.out.println(sw.prettyPrintFormat());
    }

    @Test
    void rareWordQueryTest() {
        RareWordRequest request = new RareWordRequest();
        PageResponse<RareWordResponse> response = baseDataApi.rareWordQuery(request);

        Set<String> idSet = new HashSet<>();
        for (int i = 0; i < response.getPages(); i++) {
            RareWordRequest r = new RareWordRequest();
            r.setCurrent(i);
            Set<String> ids = baseDataApi.rareWordQuery(r).getRecords().stream().map(RareWordResponse::getSerial_id).collect(Collectors.toSet());
            idSet.addAll(ids);
            System.out.println("当前页：" + i + ", 累计记录的ID数：" + idSet.size());
        }
        System.out.println("总ID数量：" + idSet.size());
    }

    @Test
    void queryAllRareWordTest() {
        List<RareWordResponse> allRareWordList = baseDataApi.queryAllRareWord();

        ConfigurationMap configMap = new ConfigurationMap();
        configMap.addProperty("name", "数据插入");
        configMap.addProperty("version", "v1");
        configMap.addProperty("fileName", "016-init-crh_user.rareword");
        configMap.addProperty("data", allRareWordList.stream().map(this::toScriptMap).collect(Collectors.toList()));
        String path = scriptGenerateApi.getSqlScript(configMap);
        System.out.println(path);
    }

    private Map<String, Object> toScriptMap(RareWordResponse response) {
        Map<String, Object> scritpMap = new LinkedHashMap<>();
        scritpMap.put("schema", "CRH_USER");
        scritpMap.put("table", "RAREWORD");
        scritpMap.put("uniqueKey", Arrays.asList("FIRST_PINYIN", "SECOND_PINYIN", "APPEND_STRING"));
        scritpMap.put("columns", Arrays.asList(
                "SERIAL_ID",
                "CHARACTER_STR",
                "FIRST_PINYIN",
                "SECOND_PINYIN",
                "APPEND_STRING",
                "CREATE_DATETIME",
                "CREATE_BY",
                "UPDATE_DATETIME",
                "UPDATE_BY",
                "REMARK"));
        scritpMap.put("SERIAL_ID", response.getSerial_id());
        scritpMap.put("CHARACTER_STR", response.getCharacter_str());
        scritpMap.put("FIRST_PINYIN", response.getFirst_pinyin());
        scritpMap.put("SECOND_PINYIN", response.getSecond_pinyin());
        scritpMap.put("APPEND_STRING", response.getAppend_string());
        scritpMap.put("CREATE_DATETIME", "[sql]sysdate");
        scritpMap.put("CREATE_BY", "SYSTEM");
        scritpMap.put("UPDATE_DATETIME", "[sql]sysdate");
        scritpMap.put("UPDATE_BY", "SYSTEM");
        scritpMap.put("REMARK", " ");
        return scritpMap;
    }


    @Test
    void mutableMapTest() {
        MutableMap<String, Object> mutableMap = new MutableMap<>();
        Map<String, Object> devMap = new HashMap<>();
        devMap.put("a", "a1-dev");
        devMap.put("b", "b2");
        devMap.put("c", "c1");
        Map<String, Object> commonMap = new HashMap<>();
        commonMap.put("a", "a1");
        commonMap.put("d", "d1");

        mutableMap.addFirst(commonMap);
        mutableMap.addFirst(devMap);
        mutableMap.forEach((k, v) -> System.out.println(k + "=" + v));

    }

    @Test
    void test() {
        System.out.println(StringUtils.toFullWidth("g"));
    }


    @Resource
    private MallApi mallApi;

    @Test
    void mallApiTest() {
        List<ConfigurationMap> allSeries = mallApi.getAllSeries(69);
        for (ConfigurationMap seriesMap : allSeries) {
            String serialNo = seriesMap.getString("serial_no");
            List<ConfigurationMap> prodList = mallApi.getProdList(serialNo);
            if (prodList.size() > 1) {
                System.out.println(serialNo);
            }
        }
    }

    @Test
    void seriesIdGetTest() {
        Set<String> allSeriesId = mallApi.getAllSeriesId();
        allSeriesId.forEach(System.out::println);
        System.out.println(allSeriesId.size());
    }

}