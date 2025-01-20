package io.github.lucklike.luckyclient.api.server.ann;

import com.luckyframework.httpclient.proxy.CommonFunctions;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.File;

@Slf4j
@SpringBootTest
class FileChunkUploadApiTest {

    @Resource
    private FileChunkUploadApi api;

    @Test
    void chunkUploadTest() throws Exception {
        File file = new File("/Users/fukang/Lucky/lucky-httpclient-example/lucky-client/src/main/resources/application.yml");
        api.uploadFile(file, 100L, 10);
    }

    @Test
    void hash() throws Exception {
        File file1 = new File("/Users/fukang/Desktop/test/bfile/ideaIU-2024.3.dmg");
        File file2 = new File("/Users/fukang/Desktop/test/shard/uploads/ideaIU-2024.3.dmg");

        String hash1 = CommonFunctions.md5Hex(file1);
        String hash2 = CommonFunctions.md5Hex(file2);
        System.out.println(hash1);
        System.out.println(hash2);
        System.out.println(hash1.equals(hash2));
    }
}