package io.github.lucklike.luckyclient.api.server.ann;

import com.luckyframework.httpclient.proxy.annotations.Get;
import com.luckyframework.httpclient.proxy.annotations.MultiData;
import com.luckyframework.httpclient.proxy.annotations.MultiFile;
import com.luckyframework.httpclient.proxy.annotations.Post;
import com.luckyframework.httpclient.proxy.annotations.QueryParam;
import io.github.lucklike.httpclient.annotation.HttpClient;

@HttpClient("http://localhost:8864/shard/upload")
public interface ShardFileUploadApi {

    @Get("check")
    @QueryParam
    Boolean check(String fileId, long chunkNumber);

    @Post("/chunk")
    String uploadChunk(@MultiData String fileId, @MultiData long chunkNumber, @MultiFile(fileName = "file-#{p1}") byte[] file);

    /**
     * 合并分片文件
     */
    @Post("/merge")
    @QueryParam
    String mergeChunks(String fileId, String fileName);
}
