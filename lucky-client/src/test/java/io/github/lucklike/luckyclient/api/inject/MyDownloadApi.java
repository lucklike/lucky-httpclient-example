package io.github.lucklike.luckyclient.api.inject;

import com.luckyframework.httpclient.generalapi.file.RangeDownloadApi;
import com.luckyframework.httpclient.proxy.annotations.BrowserFeign;
import com.luckyframework.httpclient.proxy.annotations.HttpExec;
import com.luckyframework.httpclient.proxy.annotations.PrintRequestLog;
import com.luckyframework.httpclient.proxy.annotations.UseProxy;

import java.net.Proxy;

/**
 * @author fukang
 * @version 1.0.0
 * @date 2024/11/8 01:41
 */
@HttpExec.okhttp
@PrintRequestLog
@BrowserFeign
//@UseProxy(type = Proxy.Type.SOCKS, ip = "118.25.42.139", port = "8882", username = "marry", password = "pass123")
public abstract class MyDownloadApi extends RangeDownloadApi {
}
