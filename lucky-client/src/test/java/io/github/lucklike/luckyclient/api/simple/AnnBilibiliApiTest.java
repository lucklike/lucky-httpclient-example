package io.github.lucklike.luckyclient.api.simple;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class AnnBilibiliApiTest {

    @Resource
    private AnnBilibiliApi annBilibiliApi;

    @Test
    void indexTest() {
        System.out.println(annBilibiliApi.index());
    }

}
