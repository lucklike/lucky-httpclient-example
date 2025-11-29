package io.github.lucklike.luckyclient.api.roll;

import com.luckyframework.common.Table;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class OilPriceApiTest {

    @Autowired
    private OilPriceApi api;

    @Test
    void query() throws InterruptedException {
        String provinceStr =  "安徽、北京、重庆、福建、甘肃、广东、广西、贵州、海南、河北、黑龙江、河南、湖北、湖南、江苏、江西、吉林、辽宁、内蒙古、宁夏、青海、陕西、上海、山东、山西、四川、天津、西藏、新疆、云南、浙江";
        Table t = new Table();
        t.addHeader("省份", "0#", "89#", "92#", "95#", "98#");
        queryList(t, Arrays.asList(provinceStr.split("、")));
        System.out.println(t.format());
    }

    @Test
    void testQuery() {
//        Map<String, Object> map = api.query("湖北");
//        System.out.println(map);

        System.out.println(api.query1("广东"));
//        System.out.println(api.query1("湖北"));

    }

    private void queryList(Table table, List<String> provinceList) throws InterruptedException {
        for (String province : provinceList) {
            Map<String, Object> map = api.query(province);
            table.addDataRow(province, map.get("t0"), map.get("t89"), map.get("t92"), map.get("t95"), map.get("t98"));
        }

    }
}