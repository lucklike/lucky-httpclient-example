package io.github.lucklike.luckyclient.api.spark;

import com.luckyframework.httpclient.generalapi.RangeDownloadApi;
import io.github.lucklike.httpclient.configapi.ResourceHttpClient;

import java.util.Map;

/**
 * 科大讯飞，语音合成API
 *
 * @author fukang
 * @version 1.0.0
 * @date 2024/7/27 02:13
 */
@ResourceHttpClient
public interface SpeechApi extends RangeDownloadApi {

    /**
     * 创建回调方式任务
     *
     * @param filePath 文本文件位置
     */
    void createTask(String filePath);

    /**
     * 创建任务
     *
     * @param filePath 文本文件位置
     * @return 任务ID
     */
    String create(String filePath);

    /**
     * 根据任务ID获取任务结果
     * 当条件不满足时：$body$.header.code !=0 or $body$.header.task_status != '5'
     * 会进行重试，重试时间间隔为5秒，总共重试15次
     *
     * @param task_id 任务ID
     * @return 任务结果
     */
    Map<String, Object> queryTask(String task_id);
}
