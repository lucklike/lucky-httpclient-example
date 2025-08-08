package io.github.lucklike.luckyclient.api.cairh.xpe;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class XpeApiTest {

    @Resource
    private XpeApi api;

    @Test
    void downloadModelFile() {
        File file = api.downloadModelFile("2323", "1.0");
        System.out.println(file.getAbsolutePath());
    }

    @Test
    void downloadArchFile() {
        File file = api.downloadArchFile("2339220", "e7d004fa27d2edcc1608810c4dc83324");
        System.out.println(file.getAbsolutePath());
    }

    @Test
    void getXpeModelFileTest() {
        XpeFile xpeModelFile = api.getXpeModelFile("2323", "1.0");
        System.out.println(xpeModelFile.getFileName());
    }

    @Test
    void getXpeArchFileTest() {
        XpeFile xpeArchFile = api.getXpeArchFile("2339220", "e7d004fa27d2edcc1608810c4dc83324");
        System.out.println(xpeArchFile.getFileName());
    }
}