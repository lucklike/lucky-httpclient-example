package io.github.lucklike.luckyclient.api.inject;

import com.luckyframework.httpclient.generalapi.file.RangeDownload;
import com.luckyframework.httpclient.proxy.annotations.Get;
import com.luckyframework.httpclient.proxy.annotations.PrintLogProhibition;
import com.luckyframework.httpclient.proxy.annotations.UseProxy;
import io.github.lucklike.httpclient.annotation.HttpClient;

import java.io.File;
import java.net.Proxy;

/**
 * @author fukang
 * @version 1.0.0
 * @date 2024/12/23 02:45
 */
@PrintLogProhibition
@HttpClient("https://download.jetbrains.com")
public interface ISODownloadApi {

    @Get("idea/ideaIU-2024.3.dmg")
    @RangeDownload(saveDir = "D:\\test\\iso",filename = "IDEA-1")
    File download();

    @RangeDownload("D:\\test\\iso")
    @Get("http://maven.cairenhui.com/nexus/content/repositories/crh_dev/com/cairh/cpe-common-backend/0.1.7/cpe-common-backend-0.1.7.jar")
    File jarDownload();
}
