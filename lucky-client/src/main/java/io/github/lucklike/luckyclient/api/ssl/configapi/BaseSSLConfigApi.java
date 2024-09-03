package io.github.lucklike.luckyclient.api.ssl.configapi;

import io.github.lucklike.httpclient.configapi.ResourceHttpClient;

@ResourceHttpClient("classpath:/api/ssl/#{$class$.getSimpleName()}.yml")
public interface BaseSSLConfigApi {
}
