package io.github.lucklike.luckyclient.api.util;

import io.github.lucklike.httpclient.configapi.ResourceHttpClient;

@ResourceHttpClient("classpath:api/ai/#{$class$.getSimpleName()}.yml")
public interface AI {
}
