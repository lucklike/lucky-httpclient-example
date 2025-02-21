package io.github.lucklike.luckyclient.api.cairh.comp;

import com.luckyframework.common.ConfigurationMap;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class NacosCompApiTest {

    @Resource
    private NacosCompApi api;

    @Test
    void queryExamDetail() {
        System.out.println(api.queryExamDetail("202407081649230130135"));
    }

    @Test
    void queryByPage() {
        ConfigurationMap map = api.queryByPage();
        ConfigurationMap map2 = api.queryByPage();
        System.out.println(map);
    }
}