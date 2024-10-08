package io.github.lucklike.luckyclient.api.inject;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FileServiceTest {

    @Resource
    private FileService fileService;

    @Test
    void downloadFile() {
        fileService.downloadFile("http://www.baidu.com", "D:/test");
    }
}