package io.github.lucklike.luckyclient.api.transdog;

import com.luckyframework.httpclient.generalapi.AutoVerifyHttpStatus;
import com.luckyframework.httpclient.proxy.spel.SpELImport;
import io.github.lucklike.httpclient.configapi.ResourceHttpClient;

/**
 * 翻译狗API
 */
@AutoVerifyHttpStatus(condition = "#{$status$ != 200}", errMsg = "#{$stringBody$}")
@SpELImport(FanYiGouFunction.class)
@ResourceHttpClient
public interface TranslationApi {

    String trans(String text);

}
