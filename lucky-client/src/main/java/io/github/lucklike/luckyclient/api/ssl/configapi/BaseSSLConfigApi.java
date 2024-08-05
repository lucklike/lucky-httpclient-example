package io.github.lucklike.luckyclient.api.ssl.configapi;

import io.github.lucklike.httpclient.configapi.LocalConfigHttpClient;

@LocalConfigHttpClient(source = "classpath:/api/ssl/#{$class$.getSimpleName()}.yml")
public interface BaseSSLConfigApi {
}
