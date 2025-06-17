package io.github.lucklike.luckyclient.api.mock.impl.mock;

import com.luckyframework.common.StringUtils;
import io.github.lucklike.luckyclient.api.mock.MockPluginApi;

public class MockPluginApiImpl implements MockPluginApi {
    @Override
    public String test1() {
        return "你好";
    }

    @Override
    public String agsTest(String a, Integer b) {
        return StringUtils.format("a={}, b={}", a, b);
    }

    @Override
    public String test2() {
        return "Hello";
    }
}
