package io.github.lucklike.luckyclient.api.inject;

import com.luckyframework.httpclient.generalapi.file.RangeDownloadApi;
import com.luckyframework.httpclient.proxy.annotations.BrowserFeign;
import com.luckyframework.httpclient.proxy.annotations.HttpExec;
import com.luckyframework.httpclient.proxy.annotations.PrintRequestLog;

/**
 * @author fukang
 * @version 1.0.0
 * @date 2024/11/8 01:41
 */
@HttpExec.http_client
@PrintRequestLog
@BrowserFeign
public abstract class MyDownloadApi extends RangeDownloadApi {
}
