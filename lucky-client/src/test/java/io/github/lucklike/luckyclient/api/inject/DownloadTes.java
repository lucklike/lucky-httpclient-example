package io.github.lucklike.luckyclient.api.inject;

import com.luckyframework.httpclient.generalapi.RangeDownloadApi;
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
        String url = "https://github.com/docmirror/dev-sidecar/releases/download/v1.8.9/DevSidecar-1.8.9-node16.dmg";
        File file = downloadApi.rangeFileDownload(url, "/Users/fukang/Downloads/dev-sidecar");
        System.out.println(file.getAbsolutePath());
    }
}
