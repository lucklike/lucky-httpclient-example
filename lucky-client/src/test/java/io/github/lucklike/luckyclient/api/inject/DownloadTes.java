package io.github.lucklike.luckyclient.api.inject;

import com.luckyframework.httpclient.core.meta.Request;
import com.luckyframework.httpclient.generalapi.Range;
import com.luckyframework.httpclient.generalapi.RangeDownloadApi2;
import io.github.lucklike.httpclient.annotation.HttpReference;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;

/**
 * @author fukang
 * @version 1.0.0
 * @date 2024/11/8 01:11
 */
@SpringBootTest
public class DownloadTes {

    @HttpReference
    MyDownloadApi downloadApi;

    @Test
    void download() throws Exception {
        String url = "https://github.com/docmirror/dev-sidecar/releases/download/v1.8.9/DevSidecar-1.8.9-node16.exe";
        File file = downloadApi.rangeFileDownload(url, "D:/test/dev-sidecar", 2 * 1024 * 1024);
        System.out.println(file.getAbsolutePath());
    }

    @HttpReference
    RangeDownloadApi2 downloadApi2;

    @Test
    void rangeTest() {
        Request request = Request
                .head("https://mirrors.aliyun.com/centos-stream/10-stream/BaseOS/x86_64/iso/CentOS-Stream-10-20241104.0-x86_64-boot.iso")
                .setConnectTimeout(120000)
                .setWriterTimeout(60000)
                .setReadTimeout(60000);
        Range rangeInfo = downloadApi2.rangeInfo(request);
        System.out.println(rangeInfo);
    }

    @Test
    void rangeDownloadTest() {
        Request request = Request.get("https://mirrors.aliyun.com/centos/8/isos/x86_64/CentOS-8.5.2111-x86_64-boot.iso");
        File file = downloadApi2.rangeFileDownload(request, "D:/test/iso/", null, RangeDownloadApi2.DEFAULT_RANGE_SIZE);
        System.out.println(file);
    }
}
