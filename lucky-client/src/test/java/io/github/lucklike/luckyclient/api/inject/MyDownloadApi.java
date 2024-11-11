package io.github.lucklike.luckyclient.api.inject;

import com.luckyframework.httpclient.generalapi.RangeDownloadApi;
import com.luckyframework.httpclient.proxy.annotations.BrowserFeign;
import com.luckyframework.httpclient.proxy.annotations.HttpExec;
import com.luckyframework.httpclient.proxy.annotations.PrintLogProhibition;
import com.luckyframework.httpclient.proxy.annotations.PrintRequestLog;
import com.luckyframework.httpclient.proxy.annotations.Timeout;

/**
 * @author fukang
 * @version 1.0.0
 * @date 2024/11/8 01:41
 */
@HttpExec.http_client
@Timeout(readTimeout = 5 * 6000, connectionTimeout = 2 * 6000)
@PrintRequestLog
@BrowserFeign
public interface MyDownloadApi extends RangeDownloadApi {
}
