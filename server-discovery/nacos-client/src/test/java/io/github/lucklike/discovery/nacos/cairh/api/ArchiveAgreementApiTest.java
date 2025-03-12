package io.github.lucklike.discovery.nacos.cairh.api;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class ArchiveAgreementApiTest {

    @Resource
    private ArchiveAgreementApi archiveAgreementApi;

    @Test
    void queryOne() {
        archiveAgreementApi.queryOne2("20240920476300200002");
    }
}