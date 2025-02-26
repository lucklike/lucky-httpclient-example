package io.github.lucklike.luckyclient.api.cairh.comp;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class NacosCompApiTest {

    @Resource
    private NacosCompApi api;

    @Test
    void queryExamDetail() {
        System.out.println(api.queryExamDetail("202407081649230130135"));
    }
}