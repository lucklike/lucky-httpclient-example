package io.github.lucklike.luckyclient.api.inject;

import com.luckyframework.httpclient.generalapi.file.RangeDownload;
import com.luckyframework.httpclient.proxy.annotations.Get;
import io.github.lucklike.httpclient.annotation.HttpClient;

import java.io.File;

/**
 * @author fukang
 * @version 1.0.0
 * @date 2024/12/23 02:45
 */
// https://mirrors.aliyun.com/centos-stream/10-stream/BaseOS/x86_64/iso/CentOS-Stream-10-latest-x86_64-dvd1.iso
@HttpClient("https://download.jetbrains.com")
public interface ISODownloadApi {

    @Get("idea/ideaIU-2024.3.dmg")
    @RangeDownload("/Users/fukang/Desktop/test")
    File download();
}
