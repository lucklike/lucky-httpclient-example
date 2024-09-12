package io.github.lucklike.luckyclient.api.cairh;

import com.luckyframework.httpclient.proxy.annotations.DownloadToLocal;
import com.luckyframework.httpclient.proxy.annotations.Get;
import com.luckyframework.httpclient.proxy.annotations.JsonBody;
import com.luckyframework.httpclient.proxy.annotations.Post;
import com.luckyframework.httpclient.proxy.annotations.PrintLogProhibition;
import com.luckyframework.httpclient.proxy.annotations.PropertiesJsonObject;
import com.luckyframework.httpclient.proxy.annotations.QueryParam;
import io.github.lucklike.luckyclient.api.cairh.annotations.CRHApi;
import io.github.lucklike.luckyclient.api.cairh.annotations.LooseBind;
import io.github.lucklike.luckyclient.api.cairh.request.JournalRequest;
import io.github.lucklike.luckyclient.api.cairh.response.QueryOperatorInfoResponse;

import java.io.File;
import java.util.Map;

@CRHApi
public interface CairhApi {

    @LooseBind
    @PropertiesJsonObject({"operator_no=#{operatorNo}"})
    @Post("/compfront/video/queryOperatorInfo")
    QueryOperatorInfoResponse queryOperatorInfo(String operatorNo);

    @PropertiesJsonObject({"productNo=#{productNo}"})
    @Post("/openapi/common/queryProduct")
    Map<String, Object> queryProduct(String productNo);

    @Post("/basedata/common-base/journal/queryByPage")
    Map<String, Object> queryJournal(@JsonBody JournalRequest request);

    @DownloadToLocal(saveDir = "D:/test/", filename = "黑名单")
    @Post("/basedata/blacklist/exportTemplate")
    File blackListTemplateDownload();

    @Get("/comp/exampaper/queryExamDetail")
    Map<String, Object> queryExamDetail(@QueryParam("exampaper_id") String id);
}
