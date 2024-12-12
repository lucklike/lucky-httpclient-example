package io.github.lucklike.luckyclient.api.cairh.comp;

import com.luckyframework.httpclient.generalapi.describe.Describe;
import com.luckyframework.httpclient.proxy.annotations.Get;
import com.luckyframework.httpclient.proxy.annotations.QueryParam;
import io.github.lucklike.luckyclient.api.cairh.annotations.CRHApi;

import java.util.Map;

@CRHApi(project = "comp")
public interface CompApi {

    @Describe("问卷查询")
    @Get("/exampaper/queryExamDetail")
    Map<String, Object> queryExamDetail(@QueryParam("exampaper_id") String id);
}
