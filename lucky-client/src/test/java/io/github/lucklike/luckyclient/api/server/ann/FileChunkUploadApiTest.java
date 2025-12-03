package io.github.lucklike.luckyclient.api.server.ann;

import com.luckyframework.httpclient.proxy.function.CommonFunctions;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.File;

import static com.luckyframework.httpclient.generalapi.download.RangeDownloadApi.DEFAULT_RANGE_SIZE;
import static com.luckyframework.httpclient.proxy.function.DigestFunctions.md5Hex;

@Slf4j
@SpringBootTest
class FileChunkUploadApiTest {

    @Resource
    private FileChunkUploadApi api;

    @Test
    void chunkUploadTest() throws Exception {
        File file = new File("D:\\test\\bfile\\java-ideaIU-2024.3.dmg");
        api.uploadFile(file, DEFAULT_RANGE_SIZE, 10);
    }

    @Test
    void hash() throws Exception {
        File file1 = new File("D:\\test\\bfile\\java-ideaIU-2024.3.dmg");
        File file2 = new File("D:\\test\\shard\\uploads\\java-ideaIU-2024.3.dmg");

        String hash1 = md5Hex(file1);
        String hash2 = md5Hex(file2);
        System.out.println(hash1);
        System.out.println(hash2);
        System.out.println(hash1.equals(hash2));
    }
}