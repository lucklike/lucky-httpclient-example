package io.github.lucklike.luckyclient.api.inject;

import com.luckyframework.httpclient.generalapi.download.RangeDownload;
import com.luckyframework.httpclient.proxy.annotations.AsyncExecutor;
import com.luckyframework.httpclient.proxy.annotations.DownloadToLocal;
import com.luckyframework.httpclient.proxy.annotations.Get;
import com.luckyframework.httpclient.proxy.annotations.PrintLogProhibition;
import com.luckyframework.httpclient.proxy.annotations.ResultHandlerMeta;
import com.luckyframework.httpclient.proxy.handle.ResultHandler;
import io.github.lucklike.httpclient.annotation.HttpClient;
import io.github.lucklike.httpclient.annotation.HttpReference;

import java.io.File;
import java.util.Optional;

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
    @Get("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRFIdIs43Soicnnf_Q6RDtjKBJFFW2e0iAoaA&s")
//    @Get("http://maven.cairenhui.com/nexus/content/repositories/crh_dev/com/cairh/cpe-common-backend/0.1.7/cpe-common-backend-0.1.7.jar")
    Optional<File> jarDownload();

    @RangeDownload(saveDir = "/Users/fukang/Desktop/test/bfile")
    @Get("https://github.com/docmirror/dev-sidecar/releases/download/v2.0.0/DevSidecar-2.0.0-macos-x64.dmg")
    void jarDownloadHandler(ResultHandler<File> fileHandler);

    @RangeDownload(rangeSize = 1000)
    @ResultHandlerMeta(handlerClass = FilePathPrintResultHandler.class)
    @Get("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRFIdIs43Soicnnf_Q6RDtjKBJFFW2e0iAoaA&s")
    void jarDownloadHandler();


    @RangeDownload(saveDir =  "D:/test/bfile")
    @Get("https://ollama.com/download/OllamaSetup.exe")
    File ollamaDownload();

    @RangeDownload(saveDir =  "D:/test/bfile")
    @Get("https://chatboxai.app/zh/install?download=win64")
    File chatboxaiDownload();
}
