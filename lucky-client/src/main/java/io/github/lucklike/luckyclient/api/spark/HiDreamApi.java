package io.github.lucklike.luckyclient.api.spark;

import io.github.lucklike.httpclient.configapi.ResourceHttpClient;

/**
 * 讯飞火星大模型API-HiDream 图片生成
 * @author fukang
 * @version 1.0.0
 * @date 2024/7/15 23:09
 */
@ResourceHttpClient
public interface HiDreamApi {

    String createTask(String prompt, int count);

    String queryTask(String taskId);
}
