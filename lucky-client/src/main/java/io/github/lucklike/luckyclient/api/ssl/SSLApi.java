package io.github.lucklike.luckyclient.api.ssl;

import io.github.lucklike.httpclient.configapi.LocalConfigHttpClient;

/**
 * @author fukang
 * @version 1.0.0
 * @date 2024/8/4 01:36
 */
@LocalConfigHttpClient
public interface SSLApi {

    String test();
}
