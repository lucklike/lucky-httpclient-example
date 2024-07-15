package io.github.lucklike.luckyclient.api.spark;

import cn.hutool.core.codec.Base64;
import com.luckyframework.common.NanoIdUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.FileCopyUtils;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;

/**
 * @author fukang
 * @version 1.0.0
 * @date 2024/7/15 23:33
 */
@SpringBootTest
public class HiDreamApiTest {

    @Resource
    private HiDreamApi hiDreamApi;

    @Test
    void createTaskTest() {
        String taskId = hiDreamApi.createTask("太阳系", 2);
        System.out.println(taskId);
    }

    @Test
    void queryTaskTest() throws IOException {
        String imageBase64 = hiDreamApi.queryTask("240716000723840141098315");
        String fileName = NanoIdUtils.randomNanoId() + ".jpg";;
        File file = new File("/Users/fukang/Desktop/" + fileName);
        FileCopyUtils.copy(Base64.decode(imageBase64), file);
        System.out.println(file.getAbsolutePath());
    }
}
