package io.github.lucklike.luckyclient.api.ssl.configapi;

import com.luckyframework.httpclient.proxy.configapi.EnableLocalConfigParser;

@EnableLocalConfigParser(source = "classpath:/api/ssl/#{$class$.getSimpleName()}.yml")
public interface BaseSSLConfigApi {
}
