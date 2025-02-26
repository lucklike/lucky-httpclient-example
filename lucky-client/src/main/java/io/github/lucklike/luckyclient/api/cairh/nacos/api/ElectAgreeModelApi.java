package io.github.lucklike.luckyclient.api.cairh.nacos.api;

import com.luckyframework.common.ConfigurationMap;
import com.luckyframework.httpclient.proxy.annotations.JsonBody;
import com.luckyframework.httpclient.proxy.annotations.Post;
import com.luckyframework.httpclient.proxy.annotations.StaticJsonBody;
import io.github.lucklike.luckyclient.api.cairh.nacos.NacosClient;

import java.util.Map;

@NacosClient(server = "comp-backend", contextPath = "elecagreemodel")
public interface ElectAgreeModelApi {

    @Post("queryByPage")
    @StaticJsonBody("{\"cur_page\":1,\"page_size\":10,\"modify_datetime\":\"20250220 000000\",\"current\":1,\"size\":10,\"_mdc_device_id\":\"B-9m4db9hoig\",\"timestamp\":1740039222708}")
    ConfigurationMap queryByPage();

    @Post("queryOne")
    ConfigurationMap queryOne(@JsonBody Map<String, Object> body);
}
