package io.github.lucklike.luckyclient.api.transdog;

import com.luckyframework.httpclient.proxy.spel.SpELImport;
import io.github.lucklike.httpclient.configapi.ResourceHttpClient;

/**
 * 翻译狗API
 */
@SpELImport(fun = FanYiGouFunction.class)
@ResourceHttpClient
public interface TranslationApi {

    String trans(String text);

}
