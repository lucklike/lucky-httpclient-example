package io.github.lucklike.luckyclient.api.server.ann;

import com.luckyframework.common.ContainerUtils;
import com.luckyframework.httpclient.generalapi.chunk.FileChunk;
import com.luckyframework.httpclient.generalapi.chunk.FileChunkHandle;
import com.luckyframework.httpclient.generalapi.describe.Describe;
import com.luckyframework.httpclient.proxy.CommonFunctions;
import com.luckyframework.httpclient.proxy.annotations.Get;
import com.luckyframework.httpclient.proxy.annotations.MultiData;
import com.luckyframework.httpclient.proxy.annotations.MultiFile;
import com.luckyframework.httpclient.proxy.annotations.Post;
import com.luckyframework.httpclient.proxy.annotations.QueryParam;
import io.github.lucklike.httpclient.annotation.HttpClient;
import lombok.SneakyThrows;
import org.springframework.lang.NonNull;

import java.io.File;
import java.util.Map;

/**
 * 分片文件上传API
 */
@HttpClient("http://localhost:8864/shard/upload")
public interface FileChunkUploadApi {

    //-----------------------------------------------------------------
    //                          Http Api
    //-----------------------------------------------------------------


    @Get("uploadChunks")
    @Describe("查询服务器中已上传的文件分片信息")
    Map<Integer, String> uploadChunks(@QueryParam String fileId);

    @Post("/chunk")
    @MultiData
    @Describe("上传分片文件")
    String uploadChunk(String fileId, long chunkNumber, @MultiFile(fileName = "file-#{p1}") byte[] file);

    @Post("/merge")
    @Describe("通知服务器进行文件合并")
    String mergeChunks(@QueryParam String fileId, @QueryParam String fileName);

    //-----------------------------------------------------------------
    //                         Upload Method
    //-----------------------------------------------------------------

    /**
     * 分片文件上传
     *
     * @param file           文件对象
     * @param chunkSize      分片大小
     * @param maxConcurrency 最大并发数
     * @throws Exception 上传过程中可能出现的异常
     */
    default void uploadFile(File file, long chunkSize, int maxConcurrency) throws Exception {

        // 获取文件名和文件hash
        String fileId = CommonFunctions.md5Hex(file);
        String fileName = file.getName();

        // 获取已经上传到服务器中的分片文件信息
        Map<Integer, String> uploadChunkMap = uploadChunks(fileId);

        // 获取分片文件处理器进行处理
        FileChunkHandle handle = FileChunkHandle.of(file, chunkSize);
        handle.asyncHandle(
                sf -> {
                    if (isUploaded(uploadChunkMap, sf)) {
                        return "ok";
                    } else {
                        return uploadChunk(fileId, sf.getIndex(), sf.getContent());
                    }
                },
                rs -> {
                    String r = mergeChunks(fileId, fileName);
                    rs.forEach(System.out::println);
                    System.out.println(r);

                },
                maxConcurrency
        );
    }

    /**
     * 检查否个分片文件是否已经上传
     *
     * @param chunkMap  服务器上已有的分片文件信息
     * @param fileChunk 带检查的分片文件
     * @return 是否已经上传到服务器了
     */
    @SneakyThrows
    default boolean isUploaded(Map<Integer, String> chunkMap, @NonNull FileChunk fileChunk) {
        if (ContainerUtils.isEmptyMap(chunkMap)) {
            return false;
        }
        String hash = chunkMap.get((int) fileChunk.getIndex());
        if (hash == null) {
            return false;
        }

        return hash.equals(CommonFunctions.md5Hex(fileChunk.getContent()));
    }
}
