package io.github.lucklike.luckyclient.api.cairh;

import com.luckyframework.httpclient.generalapi.describe.Describe;
import com.luckyframework.httpclient.proxy.annotations.DownloadToLocal;
import com.luckyframework.httpclient.proxy.annotations.Get;
import com.luckyframework.httpclient.proxy.annotations.JsonBody;
import com.luckyframework.httpclient.proxy.annotations.Post;
import com.luckyframework.httpclient.proxy.annotations.PropertiesJson;
import com.luckyframework.httpclient.proxy.annotations.QueryParam;
import io.github.lucklike.luckyclient.api.cairh.annotations.CRHApi;
import io.github.lucklike.luckyclient.api.cairh.annotations.LooseBind;
import io.github.lucklike.luckyclient.api.cairh.request.JournalRequest;
import io.github.lucklike.luckyclient.api.cairh.request.RareWordRequest;
import io.github.lucklike.luckyclient.api.cairh.response.PageResponse;
import io.github.lucklike.luckyclient.api.cairh.response.QueryOperatorInfoResponse;
import io.github.lucklike.luckyclient.api.cairh.response.RareWordResponse;

import java.io.File;
import java.util.List;
import java.util.Map;

@CRHApi
public interface CairhApi {

    @LooseBind
    @Describe("操作员查询")
    @PropertiesJson
    @Post("/compfront/video/queryOperatorInfo")
    QueryOperatorInfoResponse queryOperatorInfo(String operator_no);

    @Describe("产品查询")
    @PropertiesJson
    @Post("/openapi/common/queryProduct")
    Map<String, Object> queryProduct(String productNo);

    @Describe("系统操作流水查询")
    @Post("/basedata/journal/queryByPage")
    Map<String, Object> queryJournal(@JsonBody JournalRequest request);

    @DownloadToLocal(saveDir = "D:/test/", filename = "黑名单")
    @Describe("黑名单模版导出")
    @Post("/basedata/blacklist/exportTemplate")
    File blackListTemplateDownload();

    @Describe("问卷查询")
    @Get("/comp/exampaper/queryExamDetail")
    Map<String, Object> queryExamDetail(@QueryParam("exampaper_id") String id);

    @Describe("生僻字查询")
    @Post("basedata/rareword/queryByPage")
    PageResponse<RareWordResponse> rareWordQuery(@JsonBody RareWordRequest request);

    @Describe("查询所有生僻字")
    @Post("/basedata/rareword/queryAll")
    List<RareWordResponse> queryAllRareWord();

}
