package io.github.lucklike.luckyclient.api.cairh.comp;

import com.luckyframework.common.ConfigurationMap;
import com.luckyframework.httpclient.generalapi.describe.Describe;
import com.luckyframework.httpclient.proxy.annotations.Get;
import com.luckyframework.httpclient.proxy.annotations.Post;
import com.luckyframework.httpclient.proxy.annotations.QueryParam;
import com.luckyframework.httpclient.proxy.annotations.StaticJsonBody;
import io.github.lucklike.luckyclient.api.cairh.nacos.NacosClient;

@NacosClient("comp-backend")
public interface NacosCompApi {

    @Describe("问卷查询")
    @Get("/exampaper/queryExamDetail")
    String queryExamDetail(@QueryParam("exampaper_id") String id);

    @StaticJsonBody("{\"cur_page\":1,\"page_size\":10,\"modify_datetime\":\"20250220 000000\",\"current\":1,\"size\":10,\"_mdc_device_id\":\"B-9m4db9hoig\",\"timestamp\":1740039222708}")
    @Post("/elecagreemodel/queryByPage")
    ConfigurationMap queryByPage();
}
