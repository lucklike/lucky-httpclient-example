package io.github.lucklike.discovery.nacos.cairh.api;

import com.luckyframework.httpclient.proxy.annotations.JsonParam;
import com.luckyframework.httpclient.proxy.annotations.Post;

//@NacosClient(server = "archive-backend", contextPath = "arch/archive-agreement/")
public interface ArchiveAgreementApi {

    @Post("queryOne")
    String queryOne(@JsonParam String serial_id);

    @Post("https://glakh.cpetest.cairenhui.com/api/arch/archive-agreement/queryOne")
    String queryOne2(@JsonParam String serial_id);
}
