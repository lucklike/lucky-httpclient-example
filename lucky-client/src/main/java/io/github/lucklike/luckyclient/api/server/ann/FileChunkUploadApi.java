package io.github.lucklike.luckyclient.api.server.ann;

import com.luckyframework.httpclient.generalapi.describe.Describe;
import com.luckyframework.httpclient.proxy.annotations.Get;
import com.luckyframework.httpclient.proxy.annotations.MultiData;
import com.luckyframework.httpclient.proxy.annotations.MultiFile;
import com.luckyframework.httpclient.proxy.annotations.Post;
import com.luckyframework.httpclient.proxy.annotations.QueryParam;
import io.github.lucklike.httpclient.annotation.HttpClient;

import java.util.Map;

@HttpClient("http://localhost:8864/shard/upload")
public interface FileChunkUploadApi {


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
}
