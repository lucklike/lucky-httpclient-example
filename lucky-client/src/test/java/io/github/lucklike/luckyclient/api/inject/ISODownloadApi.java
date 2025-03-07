package io.github.lucklike.luckyclient.api.inject;

import com.luckyframework.httpclient.generalapi.download.RangeDownload;
import com.luckyframework.httpclient.proxy.annotations.AsyncExecutor;
import com.luckyframework.httpclient.proxy.annotations.DownloadToLocal;
import com.luckyframework.httpclient.proxy.annotations.Get;
import com.luckyframework.httpclient.proxy.annotations.PrintLogProhibition;
import com.luckyframework.httpclient.proxy.annotations.ResultHandlerMeta;
import com.luckyframework.httpclient.proxy.annotations.UseProxy;
import com.luckyframework.httpclient.proxy.async.Model;
import com.luckyframework.httpclient.proxy.handle.ResultHandler;
import io.github.lucklike.httpclient.annotation.HttpClient;
import io.github.lucklike.httpclient.annotation.HttpReference;

import java.io.File;
import java.net.Proxy;
import java.util.Optional;

/**
 * @author fukang
 * @version 1.0.0
 * @date 2024/12/23 02:45
 */
@HttpClient("https://download.jetbrains.com")
public interface ISODownloadApi {

    @Get("idea/ideaIU-2024.3.dmg")
    @RangeDownload(saveDir = "D:/test/bfile")
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

    @AsyncExecutor("pool1")
    @RangeDownload(saveDir = "D:/test/bfile/", filename = "java-{_name_}{.ext}")
    @Get("idea/ideaIU-2024.3.dmg")
    File threadModelDownload();

    @AsyncExecutor(executor = "pool1", model = Model.KOTLIN_COROUTINE)
    @RangeDownload(saveDir = "D:/test/bfile/", filename = "kotlin-{_name_}{.ext}")
    @Get("idea/ideaIU-2024.3.dmg")
    File coModelDownload();
}
