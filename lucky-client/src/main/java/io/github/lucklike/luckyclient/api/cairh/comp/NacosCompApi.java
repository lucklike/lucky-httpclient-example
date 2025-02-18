package io.github.lucklike.luckyclient.api.cairh.comp;

import com.luckyframework.httpclient.generalapi.describe.Describe;
import com.luckyframework.httpclient.proxy.annotations.Get;
import com.luckyframework.httpclient.proxy.annotations.QueryParam;
import io.github.lucklike.luckyclient.api.cairh.nacos.NacosClient;

@NacosClient("comp-backend")
public interface NacosCompApi {

    @Describe("问卷查询")
    @Get("/exampaper/queryExamDetail")
    String queryExamDetail(@QueryParam("exampaper_id") String id);
}
