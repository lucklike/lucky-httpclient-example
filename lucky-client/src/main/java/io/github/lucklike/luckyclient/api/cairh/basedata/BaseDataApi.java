package io.github.lucklike.luckyclient.api.cairh.basedata;


import com.luckyframework.httpclient.generalapi.describe.Describe;
import com.luckyframework.httpclient.proxy.annotations.DownloadToLocal;
import com.luckyframework.httpclient.proxy.annotations.JsonBody;
import com.luckyframework.httpclient.proxy.annotations.JsonParam;
import com.luckyframework.httpclient.proxy.annotations.Post;
import com.luckyframework.httpclient.proxy.annotations.PrintLogProhibition;
import com.luckyframework.httpclient.proxy.annotations.PropertiesJson;
import com.luckyframework.httpclient.proxy.annotations.Wrapper;
import io.github.lucklike.luckyclient.api.cairh.annotations.CRHApi;
import io.github.lucklike.luckyclient.api.cairh.basedata.resp.OperInfoQry;
import io.github.lucklike.luckyclient.api.cairh.basedata.req.JournalRequest;
import io.github.lucklike.luckyclient.api.cairh.basedata.req.RareWordRequest;
import io.github.lucklike.luckyclient.api.cairh.basedata.resp.PageResponse;
import io.github.lucklike.luckyclient.api.cairh.basedata.resp.RareWordResponse;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

@CRHApi(project = "basedata")
public interface BaseDataApi {

    @Wrapper("#{$this$.queryOperInfo(id).user_name}")
    Future<String> getOperName(String id);

    @Describe("操作员信息查询")
    @Post("/operInfo/queryOne")
    OperInfoQry queryOperInfo(@JsonParam String staff_no);

    @Describe("系统操作流水查询")
    @Post("/journal/queryByPage")
    Map<String, Object> queryJournal(@JsonBody JournalRequest request);

    @Describe("黑名单模版导出")
    @DownloadToLocal(saveDir = "D:/test/", filename = "黑名单")
    @Post("/blacklist/exportTemplate")
    File blackListTemplateDownload();

    @Describe("生僻字分页查询")
    @Post("/rareword/queryByPage")
    PageResponse<RareWordResponse> rareWordQuery(@JsonBody RareWordRequest request);

    @Describe("查询所有生僻字")
    @Post("/rareword/queryAll")
    List<RareWordResponse> queryAllRareWord();

    @Wrapper("#{$this$.getOneRareWord(id).source_append_string}")
    Future<String> getOneRareWordSource(String id);

    @PrintLogProhibition
    @Describe("查询单个生僻字")
    @Post("/rareword/queryOne")
    RareWordResponse getOneRareWord(@JsonParam String serial_id);
}
