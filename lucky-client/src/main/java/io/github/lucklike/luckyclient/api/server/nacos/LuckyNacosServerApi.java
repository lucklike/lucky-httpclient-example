package io.github.lucklike.luckyclient.api.server.nacos;

import com.luckyframework.httpclient.proxy.annotations.Get;
import com.luckyframework.httpclient.proxy.annotations.PrintLogProhibition;

/**
 * @author fukang
 * @version 1.0.0
 * @date 2025/2/18 23:11
 */
@PrintLogProhibition
@NacosHttpClient(name = "lucky-server", group = "lucky-group")
public interface LuckyNacosServerApi {

    @Get("hello")
    String hello();
}
