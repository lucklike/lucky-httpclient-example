package io.github.lucklike.luckyclient.api.cairh.nacos.api;

import com.luckyframework.httpclient.proxy.annotations.JsonParam;
import com.luckyframework.httpclient.proxy.annotations.Post;
import io.github.lucklike.luckyclient.api.cairh.nacos.NacosClient;

@NacosClient(server = "archive-backend", contextPath = "arch/archive-agreement/")
public interface ArchiveAgreementApi {

    @Post("queryOne")
    String queryOne(@JsonParam String serial_id);

    @Post("https://glakh.cpetest.cairenhui.com/api/arch/archive-agreement/queryOne")
    String queryOne2(@JsonParam String serial_id);
}
