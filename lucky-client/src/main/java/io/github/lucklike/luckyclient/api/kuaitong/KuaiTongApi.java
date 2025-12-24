package io.github.lucklike.luckyclient.api.kuaitong;

import com.luckyframework.httpclient.generalapi.token.UseTokenManager;
import io.github.lucklike.httpclient.configapi.ResourceHttpClient;

/**
 * 快瞳API
 */
@ResourceHttpClient
public interface KuaiTongApi {

    /**
     * 获取访问Token
     *
     * @return 访问Token
     */
    @UseTokenManager("${user.dir}/kt_token.json")
    Token token();

    /**
     * 身份证OCR识别
     *
     * @param idCardPath 身份证图片路径
     * @return 身份证信息
     */
    IdentityInfo identityCardOcr(String idCardPath);
}
