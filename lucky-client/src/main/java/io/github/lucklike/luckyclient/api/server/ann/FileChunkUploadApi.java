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
import com.luckyframework.httpclient.proxy.annotations.RespConvert;
import io.github.lucklike.entity.response.Result;
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

    /**
     * 分片文件上传
     * 具体实现逻辑在{@link FileChunkPlugin}中
     *
     * @param file           文件对象
     * @param chunkSize      分片大小
     * @param maxConcurrency 最大并发数
     */
    void uploadFile(File file, long chunkSize, int maxConcurrency);

    @Get("uploadChunks")
    @RespConvert("#{$body$.data}")
    @Describe("查询服务器中已上传的文件分片信息")
    Map<Integer, String> uploadChunks(@QueryParam String fileId);

    @Post("/chunk")
    @MultiData
    @Describe("上传分片文件")
    Result<String> uploadChunk(String fileId, long chunkNumber, @MultiFile(fileName = "file-#{p1}") byte[] file);

    @Post("/merge")
    @Describe("通知服务器进行文件合并")
    Result<String> mergeChunks(@QueryParam String fileId, @QueryParam String fileName);

}
