package io.github.lucklike.luckyclient.api.plugin;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author fukang
 * @version 1.0.0
 * @date 2025/1/30 01:49
 */
@SpringBootTest
class PluginApiTest {

    @Autowired
    private PluginApi pluginApi;

    @Test
    void pluginTest() {
        pluginApi.pluginTest("12", "34");
    }
}