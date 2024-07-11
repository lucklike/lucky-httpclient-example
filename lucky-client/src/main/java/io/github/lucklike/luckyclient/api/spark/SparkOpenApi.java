package io.github.lucklike.luckyclient.api.spark;

import io.github.lucklike.httpclient.configapi.LocalConfigHttpClient;

@LocalConfigHttpClient
public interface SparkOpenApi {

    /**
     * 讯飞火星-大模型问答
     *
     * @param content 提问内容
     */
    void completions(String content);

    /**
     * 科大讯飞-身份证识别
     *
     * @param path 身份证图片路径
     * @return 识别后的文本内容
     */
    String idCardOcr(String path);

}
