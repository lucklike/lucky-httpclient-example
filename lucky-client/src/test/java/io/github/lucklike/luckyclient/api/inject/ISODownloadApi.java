package io.github.lucklike.luckyclient.api.inject;

import com.luckyframework.httpclient.generalapi.download.RangeDownload;
import com.luckyframework.httpclient.proxy.annotations.AsyncExecutor;
import com.luckyframework.httpclient.proxy.annotations.DownloadToLocal;
import com.luckyframework.httpclient.proxy.annotations.Get;
import com.luckyframework.httpclient.proxy.annotations.PrintLogProhibition;
import com.luckyframework.httpclient.proxy.handle.ResultHandler;
import io.github.lucklike.httpclient.annotation.HttpClient;
import io.github.lucklike.httpclient.annotation.HttpReference;

import java.io.File;

/**
 * @author fukang
 * @version 1.0.0
 * @date 2024/12/23 02:45
 */
@PrintLogProhibition
@HttpClient("https://download.jetbrains.com")
public interface ISODownloadApi {

    @AsyncExecutor(concurrency = "15")
    @Get("idea/ideaIU-2024.3.dmg")
    @RangeDownload(saveDir = "D:/test/bfile")
    File download();

    @RangeDownload
    @Get("http://maven.cairenhui.com/nexus/content/repositories/crh_dev/com/cairh/cpe-common-backend/0.1.7/cpe-common-backend-0.1.7.jar")
    File jarDownload();

    @DownloadToLocal
    @Get("http://maven.cairenhui.com/nexus/content/repositories/crh_dev/com/cairh/cpe-common-backend/0.1.7/cpe-common-backend-0.1.7.jar")
    void jarDownloadHandler(ResultHandler<File> fileHandler);


    @RangeDownload(saveDir =  "D:/test/bfile")
    @Get("https://ollama.com/download/OllamaSetup.exe")
    File ollamaDownload();

    @RangeDownload(saveDir =  "D:/test/bfile")
    @Get("https://chatboxai.app/zh/install?download=win64")
    File chatboxaiDownload();
}
