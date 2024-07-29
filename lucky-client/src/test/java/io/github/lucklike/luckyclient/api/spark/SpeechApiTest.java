package io.github.lucklike.luckyclient.api.spark;

import com.luckyframework.common.StringUtils;
import com.luckyframework.io.MultipartFile;
import io.github.lucklike.luckyclient.util.OSProperties;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author fukang
 * @version 1.0.0
 * @date 2024/7/27 04:26
 */
@SpringBootTest
public class SpeechApiTest {

    @Resource
    private OSProperties osProperties;

    @Resource
    private SpeechApi speechApi;

    @Test
    void test() throws Exception {
        String task_id = speechApi.create("classpath:slf.txt");
        Map<String, Object> mp4Map = speechApi.queryTask(task_id);
        System.out.println(mp4Map);
        MultipartFile file = speechApi.getFile(mp4Map.get("url").toString());
        file.setFileName(StringUtils.format("{}.mp3", task_id));
        file.progressBarCopy(osProperties.get("resources"));
    }
}
