package io.github.lucklike.luckyclient.api.spark;

import com.luckyframework.httpclient.proxy.configapi.EnableLocalConfigParser;

@EnableLocalConfigParser
public interface SparkOpenApi {

    void completions(String content);
}
