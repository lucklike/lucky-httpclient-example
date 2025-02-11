package io.github.lucklike.luckyclient.api.plugin;

import com.luckyframework.common.Console;
import io.github.lucklike.httpclient.annotation.HttpClientComponent;

/**
 * @author fukang
 * @version 1.0.0
 * @date 2025/1/30 01:44
 */

@HttpClientComponent
@UseMyPlugin
public abstract class PluginApi {


    @UseMyPlugin
    public void pluginTest(String a1, String a2) {
        Console.println("Plugin Test: a1={}, a2={}", a1, a2);
    }
}
