package io.github.lucklike.luckyclient.api.inject;

import com.luckyframework.common.StopWatch;
import com.luckyframework.common.UnitUtils;
import com.luckyframework.httpclient.core.meta.Request;
import com.luckyframework.httpclient.generalapi.BaseApi;
import com.luckyframework.httpclient.generalapi.download.RangeDownloadApi;
import com.luckyframework.httpclient.proxy.CommonFunctions;
import com.luckyframework.threadpool.ThreadPoolFactory;
import com.luckyframework.threadpool.ThreadPoolParam;
import io.github.lucklike.httpclient.annotation.HttpReference;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

/**
 * @author fukang
 * @version 1.0.0
 * @date 2024/11/8 01:11
 */
@SpringBootTest
public class DownloadTest {

    @HttpReference
    MyDownloadApi myDownloadApi;

    @HttpReference
    RangeDownloadApi downloadApi;

    @HttpReference
    BaseApi baseApi;

    @Resource
    ISODownloadApi isoDownloadApi;

    @Test
    void rangeTest1() {
        StopWatch sw = new StopWatch();
        sw.start("chatboxai");
        File download = isoDownloadApi.chatboxaiDownload();
        System.out.println(download.getAbsolutePath());
        sw.stopWatch();
        System.out.println(sw.prettyPrintFormat());
    }

    @Test
    void testJarDownload() {
        StopWatch sw = new StopWatch();
        sw.start("jar");
        File download = isoDownloadApi.jarDownload();
        System.out.println(download.getAbsolutePath());
        sw.stopWatch();
        System.out.println(sw.prettyPrintFormat());
    }

    @Test
    void download() throws Exception {
        String url = "https://github.com/docmirror/dev-sidecar/releases/download/v1.8.9/DevSidecar-1.8.9-node16.exe";
        File file = downloadApi.downloadRetryIfFail(url, "D:/test/dev-sidecar");
        System.out.println(file.getAbsolutePath());
    }



    @Test
    void rangeTest() {
        Request request = Request
                .head("https://mirrors.aliyun.com/centos-stream/10-stream/BaseOS/x86_64/iso/CentOS-Stream-10-20241104.0-x86_64-boot.iso")
                .setConnectTimeout(120000)
                .setWriterTimeout(60000)
                .setReadTimeout(60000);
        boolean rangeInfo = downloadApi.isSupport(request);
        System.out.println(rangeInfo);
    }

    @Test
    void rangeDownloadTest() {
        Request request = Request.get("https://download.jetbrains.com/idea/ideaIU-2024.3.dmg");
        File file1 = downloadApi.downloadRetryIfFail(request, "/Users/fukang/Downloads/EdgeDownload/");
        System.out.println(file1.getAbsolutePath());
    }

    @Test
    void rangeDownloadTest2() {
        ThreadPoolParam poolParam = new ThreadPoolParam();
        poolParam.setCorePoolSize(5);
        poolParam.setMaximumPoolSize(15);
        poolParam.setNameFormat("Range-Task-");
        String url = "https://mirrors.aliyun.com/centos/8/isos/x86_64/CentOS-8.5.2111-x86_64-boot.iso";
        File file = myDownloadApi.downloadRetryIfFail(ThreadPoolFactory.createThreadPool(poolParam), url, "D:/test/iso", "centos-httpclient-enhance-future-factory-exc");
        System.out.println(file.getAbsolutePath());
    }

    public static void main(String[] args) throws Exception {
        File file = new File("/private/var/folders/6v/blntps_n3ts8_4v2ls2dtgl80000gn/T/Lucky/@RangeDownload/20250119/http-client-ideaIU-2024.3.dmg");
        long length = file.length();
        System.out.println(length);
        System.out.println(UnitUtils.byteTo(length));

        File file1 = new File("/private/var/folders/6v/blntps_n3ts8_4v2ls2dtgl80000gn/T/Lucky/@RangeDownload/20250119/http-client-Za_aixQdLYZUVEU5NkZJc-ideaIU-2024.3.dmg");
        long length1 = file1.length();
        System.out.println(length1);
        System.out.println(UnitUtils.byteTo(length1));

//        File file2 = new File("D:/test/iso/centos-httpclient-exc.iso");
//        long length2 = file2.length();
//        System.out.println(length2);
//        System.out.println(UnitUtils.byteTo(length2));
//
//        File file3 = new File("D:/test/iso/centos-okhttp-exc.iso");
//        long length3 = file3.length();
//        System.out.println(length3);
//        System.out.println(UnitUtils.byteTo(length3));
//        // centos-httpclient-enhance-future-factory-exc.iso
//
//        File file4 = new File("D:/test/iso/centos-httpclient-enhance-future-factory-exc.iso");
//        long length4 = file4.length();
//        System.out.println(length4);
//        System.out.println(UnitUtils.byteTo(length4));

        String md5 = CommonFunctions.sha224Hex(file);
        String md51 = CommonFunctions.sha224Hex(file1);
//        String md52 = CommonFunctions.md5(file2);
//        String md53 = CommonFunctions.md5(file3);
//        String md54 = CommonFunctions.md5(file4);

        System.out.println(md5);
        System.out.println(md51);
//        System.out.println(md52);
//        System.out.println(md53);
//        System.out.println(md54);
        System.out.println(Objects.equals(md5, md51));
//        System.out.println(Objects.equals(md5, md52));
//        System.out.println(Objects.equals(md5, md53));
//        System.out.println(Objects.equals(md5, md54));
    }
}
