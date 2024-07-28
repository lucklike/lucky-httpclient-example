package io.github.lucklike.luckyclient.controller;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.luckyframework.common.ConfigurationMap;
import com.luckyframework.io.MultipartFile;
import io.github.lucklike.luckyclient.api.spark.SpeechApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * 科大讯飞语音合成接口回调
 * @author fukang
 * @version 1.0.0
 * @date 2024/7/27 01:56
 */
@Slf4j
@RestController
@RequestMapping("/api/speech")
public class SpeechSynthesisController {

    @Resource
    private SpeechApi speechApi;

    @GetMapping("/create")
    void createTask() throws IOException {
        speechApi.createTask("classpath:slf.txt");
    }

//    public void callback()

    @PostMapping(value = "/callback", consumes = "application/octet-stream" )
    public void callback(@RequestBody byte[] bytes) {
        JSONObject createRespObj = JSONUtil.parseObj(new String(bytes, StandardCharsets.UTF_8));
        ConfigurationMap configMap = new ConfigurationMap(createRespObj);
        log.info("the callback is triggered, request body = {}", createRespObj);
        int code = configMap.getIntOrDefault("header.code", -1);
        if (code != 0) {
            log.error("callback task failed, code = {}, message = {}", code, configMap.getString("header.message"));
        }
        // 判断任务状态
        int taskStatus = configMap.getInt("header.task_status");
        if (taskStatus == 5) {
            // 任务完成，处理结果
            if (configMap.isEmpty()) {
                return;
            }

            String taskId = configMap.getString("header.task_id");
            String audioBase64 = configMap.getString("payload.audio.audio");
            String encoding = configMap.getString("payload.audio.encoding");

            byte[] decode = Base64.getDecoder().decode(audioBase64);
            String audioUrl = new String(decode);
            log.info("audio download url = {}", audioUrl);

            // 下载结果数据并写成文件，本例中选择用 task id 作为文件名称
            MultipartFile file = speechApi.getFile(audioUrl);
            String savePath = "/Users/fukang/Lucky/lucky-httpclient-example/lucky-client/src/main/resources/output/";
            file.setFileName(taskId + "." + encoding);
            try {
                file.copyToFolder(savePath);
                log.info("audio save path = {}", savePath + taskId + "." + encoding);
            } catch (IOException e) {
                log.error("write file failed", e);
            }
        }
    }
}
