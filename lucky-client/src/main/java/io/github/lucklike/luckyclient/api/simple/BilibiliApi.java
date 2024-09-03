package io.github.lucklike.luckyclient.api.simple;

import io.github.lucklike.httpclient.configapi.ResourceHttpClient;

@ResourceHttpClient
public interface BilibiliApi {

    String index(String test);
}
