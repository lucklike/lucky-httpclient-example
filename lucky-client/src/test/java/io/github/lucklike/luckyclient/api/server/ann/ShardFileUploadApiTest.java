package io.github.lucklike.luckyclient.api.server.ann;

import com.luckyframework.httpclient.generalapi.shard.FileShardHandle;
import com.luckyframework.httpclient.proxy.CommonFunctions;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class ShardFileUploadApiTest {

    @Resource
    private ShardFileUploadApi api;

    @Test
    void check() throws Exception {

        File file = new File("C:\\Users\\18143\\AppData\\Local\\Temp\\Lucky\\@RangeDownload\\20250120\\ideaIU-2024.3.dmg");
        String fileId = CommonFunctions.md5Hex(file);
        String fileName = file.getName();
//        api.mergeChunks(fileId, fileName);
        FileShardHandle handle = FileShardHandle.of(file);
        handle.asyncHandle(
                sf -> {
                    long index = sf.getShard().getIndex();
                    if (!api.check(fileId, index)) {
                        try {
                            api.uploadChunk(fileId, index, sf.getContent());
                        } catch (IOException e) {
                           log.error("upload chunk failed", e);
                        }
                    }
                },
                () -> api.mergeChunks(fileId, fileName),
                10
        );

        System.out.println(fileId);
    }


    @Test
    void hash() throws Exception {
        File file1 = new File("D:\\test\\uploads\\ideaIU-2024.3.dmg");
        File file2 = new File("C:\\Users\\18143\\AppData\\Local\\Temp\\Lucky\\@RangeDownload\\20250120\\ideaIU-2024.3.dmg");

        String hash1 = CommonFunctions.md5Hex(file1);
        String hash2 = CommonFunctions.md5Hex(file2);
        System.out.println(hash1);
        System.out.println(hash2);
        System.out.println(hash1.equals(hash2));
    }
}