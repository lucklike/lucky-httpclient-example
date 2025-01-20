package io.github.lucklike.luckyclient.api.server.ann;

import com.luckyframework.common.ContainerUtils;
import com.luckyframework.conversion.ConversionUtils;
import com.luckyframework.httpclient.generalapi.chunk.FileChunk;
import com.luckyframework.httpclient.generalapi.chunk.FileChunkHandle;
import com.luckyframework.httpclient.proxy.CommonFunctions;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.lang.NonNull;

import javax.annotation.Resource;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@SpringBootTest
class FileChunkUploadApiTest {

    @Resource
    private FileChunkUploadApi api;

    @Test
    void chunkUploadTest() throws Exception {
        File file = new File("/Users/fukang/Lucky/lucky-httpclient-example/lucky-client/src/main/resources/application.yml");
        String fileId = CommonFunctions.md5Hex(file);
        String fileName = file.getName();

        Map<Integer, String> chunkMap = api.uploadChunks(fileId);
        FileChunkHandle handle = FileChunkHandle.of(file, 100);
        handle.asyncHandle(
                sf -> {
                    if (isUploaded(chunkMap, sf)) {
                        return "ok";
                    } else {
                        return api.uploadChunk(fileId, sf.getIndex(), sf.getContent());
                    }
                },
                rs -> {
                    String r = api.mergeChunks(fileId, fileName);
                    rs.forEach(System.out::println);
                    System.out.println(r);

                },
                10
        );

        System.out.println(fileId);
    }

    @SneakyThrows
    public boolean isUploaded(Map<Integer, String> chunkMap, @NonNull FileChunk fileChunk) {
        if (ContainerUtils.isEmptyMap(chunkMap)) {
            return false;
        }
        String hash = chunkMap.get((int) fileChunk.getIndex());
        if (hash == null) {
            return false;
        }

        return hash.equals(CommonFunctions.md5Hex(fileChunk.getContent()));
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