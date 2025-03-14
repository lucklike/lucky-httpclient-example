package io.github.lucklike.common.api;

import com.luckyframework.httpclient.proxy.annotations.Get;
import com.luckyframework.httpclient.proxy.plugin.Plugin;
import io.github.lucklike.common.plugn.TimeStatisticsPlugin;
import io.github.lucklike.entity.request.User;
import io.github.lucklike.entity.response.Result;
import io.github.lucklike.httpclient.discovery.HttpClient;

import static io.github.lucklike.common.Config.SERVICE_NAME_CONFIG;
import static io.github.lucklike.common.Config.URL_CONFIG;

/**
 * @author fukang
 * @version 1.0.0
 * @date 2025/3/13 22:08
 */
//@Plugin(pluginClass = TimeStatisticsPlugin.class)
@HttpClient(url = URL_CONFIG, service = SERVICE_NAME_CONFIG, path = "user")
public interface UserApi {

    @Get("get/#{id}")
    Result<User> getUser(String id);
}
