package io.github.lucklike.luckyclient.api.util;

import io.github.lucklike.httpclient.configapi.LocalConfigHttpClient;

@LocalConfigHttpClient("classpath:api/ai/#{$class$.getSimpleName()}.yml")
public interface AI {
}
