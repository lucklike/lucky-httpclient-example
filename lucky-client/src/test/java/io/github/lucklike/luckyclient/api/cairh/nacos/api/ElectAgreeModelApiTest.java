package io.github.lucklike.luckyclient.api.cairh.nacos.api;

import com.luckyframework.common.ConfigurationMap;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ElectAgreeModelApiTest {

    @Resource
    private ElectAgreeModelApi api;

    @Test
    void queryOne() {
        Map<String, Object> params = new HashMap<>();
        params.put("serial_id", "20250221604300020001");
        ConfigurationMap result = api.queryOne(params);
        System.out.println(result);
    }
}