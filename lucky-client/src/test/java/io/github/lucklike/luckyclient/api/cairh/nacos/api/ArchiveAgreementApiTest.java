package io.github.lucklike.luckyclient.api.cairh.nacos.api;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ArchiveAgreementApiTest {

    @Resource
    private ArchiveAgreementApi archiveAgreementApi;

    @Test
    void queryOne() {
        archiveAgreementApi.queryOne2("20240920476300200002");
    }
}