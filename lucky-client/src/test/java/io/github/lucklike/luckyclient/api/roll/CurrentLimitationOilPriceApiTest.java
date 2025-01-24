package io.github.lucklike.luckyclient.api.roll;

import com.luckyframework.common.Table;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CurrentLimitationOilPriceApiTest {

    private final String provinceStr =  "安徽、北京、重庆、福建、甘肃、广东、广西、贵州、海南、河北、黑龙江、河南、湖北、湖南、江苏、江西、吉林、辽宁、内蒙古、宁夏、青海、陕西、上海、山东、山西、四川、天津、西藏、新疆、云南、浙江";
    private final List<String> provinceList = Arrays.asList(provinceStr.split("、"));

    @Autowired
    private CurrentLimitationOilPriceApi api;

    @Test
    void limitQuery() {
        queryOilPrice(api::limitQuery);
    }

    @Test
    void query() {
        queryOilPrice(api::query);
    }

    private void queryOilPrice(Function<String, Map<String, Object>> queryFunc) {
        Table table = new Table();
        table.addHeader("省份", "0#", "89#", "92#", "95#", "98#");
        for (String province : provinceList) {
            Map<String, Object> map = queryFunc.apply(province);
            table.addDataRow(province, map.get("t0"), map.get("t89"), map.get("t92"), map.get("t95"), map.get("t98"));
        }
        System.out.println(table.format());
    }
}