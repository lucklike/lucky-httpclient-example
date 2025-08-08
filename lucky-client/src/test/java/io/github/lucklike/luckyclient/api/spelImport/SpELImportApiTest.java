package io.github.lucklike.luckyclient.api.spelImport;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SpELImportApiTest {

    @Resource
    SpELImportApi spelImportApi;

    @Test
    void importTest() {
        String result = spelImportApi.importTest("Jack", "Tom");
        System.out.println(result);
    }
}