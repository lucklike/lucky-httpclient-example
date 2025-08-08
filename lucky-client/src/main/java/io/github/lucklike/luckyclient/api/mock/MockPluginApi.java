package io.github.lucklike.luckyclient.api.mock;

import com.luckyframework.httpclient.proxy.annotations.Get;
import com.luckyframework.httpclient.proxy.handle.ExceptionFallback;
import com.luckyframework.httpclient.proxy.plugin.MockPlugin;
import com.luckyframework.httpclient.proxy.plugin.MockPluginProhibition;
import io.github.lucklike.httpclient.discovery.HttpClient;
import io.github.lucklike.luckyclient.api.mock.impl.mock.MockPluginApiImpl;

@HttpClient("http://localhost:8080/mock/")
//@ExceptionFallback(MockPluginApiImpl.class)
@MockPlugin(implClass = MockPluginApiImpl.class, enable = "${mock.config.mockPluginApi.enable: false}")
public interface MockPluginApi {

    @Get("test1")
    String test1() throws Exception;

    @Get("agsTest")
    String agsTest(String a, Integer b);

    @Get("test2")
    @MockPluginProhibition
    String test2();
}
