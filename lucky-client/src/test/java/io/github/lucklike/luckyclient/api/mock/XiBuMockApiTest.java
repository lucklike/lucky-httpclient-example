package io.github.lucklike.luckyclient.api.mock;

import com.luckyframework.common.Console;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class XiBuMockApiTest {

    @Resource
    private XiBuMockApi api;

    @Test
    void black() {
        List<Map<String, Object>> black = api.black();
        System.out.println(black);
    }

    @Test
    void black2() {
        List<String> list = api.brokerIdList();
        List<String> list2 = api.brokerTelList();
        System.out.println(getRMap(list));
        System.out.println(getRMap(list2));
    }

    @Test
    void black3() {
        List<String> list = api.brokerIds();
        Map<String, Integer> rMap = getRMap(list);

        System.out.println(rMap);
        System.out.println(list.size()+ "____"+ rMap.size());
    }


    Map<String, Integer> getRMap(List<String> list) {
        Map<String, Integer> map = new HashMap<>();
        Map<String, Integer> rMap = new HashMap<>();
        for (String s : list) {
            if (map.containsKey(s)) {
                map.put(s, map.get(s) + 1);
            } else {
                map.put(s, 1);
            }
        }
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            Integer value = entry.getValue();
            if (value > 1) {
                rMap.put(entry.getKey(), value);
            }
        }

        return rMap;
    }
}