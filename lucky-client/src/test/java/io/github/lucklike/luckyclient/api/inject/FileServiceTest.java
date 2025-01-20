package io.github.lucklike.luckyclient.api.inject;

import com.luckyframework.httpclient.generalapi.download.RangeDownloadApi;
import io.github.lucklike.httpclient.annotation.HttpReference;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FileServiceTest {

    @Resource
    private FileService fileService;

    @HttpReference
    private RangeDownloadApi rangeDownloadApi;

    @Test
    void downloadFile() {
        fileService.downloadFile("https://www.bilibili.com", "D:/test");
    }

    @Test
    void rangeDownloadApiTest() {
        rangeDownloadApi.download("http://www.baidu.com", "D:/test", "baidu.htm");
    }
}