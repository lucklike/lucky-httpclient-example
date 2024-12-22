package io.github.lucklike.luckyclient.api.inject;

import com.luckyframework.httpclient.generalapi.file.RangeDownload;
import com.luckyframework.httpclient.proxy.annotations.Get;
import com.luckyframework.httpclient.proxy.annotations.PrintLogProhibition;
import io.github.lucklike.httpclient.annotation.HttpClient;

import java.io.File;

/**
 * @author fukang
 * @version 1.0.0
 * @date 2024/12/23 02:45
 */
@PrintLogProhibition
@HttpClient("https://download.jetbrains.com")
public interface ISODownloadApi {

    @Get("idea/ideaIU-2024.3.dmg")
    @RangeDownload(saveDir = "/Users/fukang/Desktop/test",filename = "IDEA")
    File download();
}
