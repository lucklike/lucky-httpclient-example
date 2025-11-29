package io.github.lucklike.luckyclient.api.inject;

import com.luckyframework.httpclient.generalapi.download.RangeDownload;
import com.luckyframework.httpclient.proxy.annotations.AsyncExecutor;
import com.luckyframework.httpclient.proxy.annotations.AutoRedirect;
import com.luckyframework.httpclient.proxy.annotations.Get;
import com.luckyframework.httpclient.proxy.annotations.HttpExec;
import com.luckyframework.httpclient.proxy.annotations.ResultHandlerMeta;
import com.luckyframework.httpclient.proxy.async.Model;
import com.luckyframework.httpclient.proxy.handle.ResultHandler;
import io.github.lucklike.httpclient.discovery.HttpClient;

import java.io.File;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * @author fukang
 * @version 1.0.0
 * @date 2024/12/23 02:45
 */
@HttpClient("https://download.jetbrains.com")
public interface ISODownloadApi {

    @Get("https://download.jetbrains.com/idea/ideaIU-2025.2.4.dmg?_gl=1*1rs411b*_gcl_au*MTk2ODYwOTU3OC4xNzU1MzMxOTQz*FPAU*MTk2ODYwOTU3OC4xNzU1MzMxOTQz*_ga*Mjg5ODIyODI3LjE3MzM2OTQyMTA.*_ga_9J976DJZ68*czE3NjIzNjMyMTckbzU2JGcxJHQxNzYyMzYzMjUyJGoyNSRsMCRoMA..")
    @RangeDownload(saveDir = "/Users/fukang/Desktop/test/bfile")
    File download();

    @RangeDownload
    @Get("https://github.com/zzzgydi/clash-verge/releases/download/v1.3.8/Clash.Verge_1.3.8_x64-setup.exe")
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

    @AsyncExecutor
    @RangeDownload(saveDir = "D:/test/bfile/", filename = "java-{_name_}{.ext}")
    @Get("idea/ideaIU-2024.3.dmg")
    File threadModelDownload();

    @AsyncExecutor(model = Model.KOTLIN_COROUTINE)
    @RangeDownload(saveDir = "D:/test/bfile/", filename = "kotlin-{_name_}{.ext}")
    @Get("idea/ideaIU-2024.3.dmg")
    File coModelDownload();


    @AsyncExecutor
    @RangeDownload(saveDir = "D:/test/bfile/", filename = "java-{_name_}{.ext}")
    @Get("idea/ideaIU-2024.3.dmg")
    CompletableFuture<File> asyncThreadModelDownload();

    @AsyncExecutor(model = Model.KOTLIN_COROUTINE)
    @RangeDownload(saveDir = "D:/test/bfile/", filename = "kotlin-{_name_}{.ext}")
    @Get("idea/ideaIU-2024.3.dmg")
    CompletableFuture<File> asyncCoModelDownload();

    @RangeDownload(saveDir = "/Users/fukang/Desktop/test/bfile/")
    @Get("https://download.jetbrains.com/idea/ideaIU-2025.1.4.1.dmg?_gl=1*oxzfst*_gcl_au*MTE3MTIyNjg2NS4xNzU1MzMxOTkx*_ga*MjE0NTY4MDk5OC4xNzU1MzMxOTkx*_ga_9J976DJZ68*czE3NTUzMzE5OTAkbzEkZzEkdDE3NTUzMzIwMDIkajQ4JGwwJGgw")
    File idea2025_1_4_1();
}
