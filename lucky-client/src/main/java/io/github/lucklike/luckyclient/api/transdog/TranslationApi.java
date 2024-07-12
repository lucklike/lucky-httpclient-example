package io.github.lucklike.luckyclient.api.transdog;

import io.github.lucklike.httpclient.configapi.LocalConfigHttpClient;

/**
 * 翻译狗API
 */
@LocalConfigHttpClient
public interface TranslationApi {

    String trans(String text);

}
