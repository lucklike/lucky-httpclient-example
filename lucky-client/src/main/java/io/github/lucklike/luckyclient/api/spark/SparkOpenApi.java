package io.github.lucklike.luckyclient.api.spark;

import com.luckyframework.httpclient.proxy.annotations.PrintLogProhibition;
import io.github.lucklike.httpclient.configapi.LocalConfigHttpClient;

import java.util.Map;

@LocalConfigHttpClient
public interface SparkOpenApi {

    /**
     * 讯飞火星-大模型问答
     *
     * @param content 提问内容
     */
    @PrintLogProhibition
    void completions(String content);

    /**
     * 科大讯飞-身份证识别
     *
     * @param path 身份证图片路径
     * @return 识别后的文本内容
     */
    Map<String, Object> idCardOcr(String path);


    /**
     * 科大讯飞-图片生成
     *
     * @param content 图片的描述信息
     * @return 根据描述信息生成的图片
     */
    String imageGenerate(String content);

}
