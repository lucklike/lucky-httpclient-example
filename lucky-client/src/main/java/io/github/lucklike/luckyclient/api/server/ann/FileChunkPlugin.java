package io.github.lucklike.luckyclient.api.server.ann;

import com.luckyframework.common.ContainerUtils;
import com.luckyframework.httpclient.generalapi.chunk.FileChunk;
import com.luckyframework.httpclient.generalapi.chunk.FileChunkHandle;
import com.luckyframework.httpclient.generalapi.chunk.ResultClassify;
import com.luckyframework.httpclient.proxy.function.CommonFunctions;
import com.luckyframework.httpclient.proxy.plugin.ExecuteMeta;
import com.luckyframework.httpclient.proxy.plugin.ProxyDecorator;
import com.luckyframework.httpclient.proxy.plugin.ProxyPlugin;
import io.github.lucklike.entity.response.Result;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Lazy;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.File;
import java.util.Map;
import java.util.Objects;

import static com.luckyframework.httpclient.proxy.function.MessageDigestFunctions.md5Hex;

/**
 * 分片文件上传插件
 *
 * @author fukang
 * @version 1.0.0
 * @date 2025/1/30 00:29
 */
@Component
public class FileChunkPlugin implements ProxyPlugin {

    @Lazy
    @Resource
    private FileChunkUploadApi uploadApi;

    @Override
    public Object decorate(ProxyDecorator decorator) throws Throwable {
        ExecuteMeta meta = decorator.getMeta();
        Object[] args = meta.getArgs();
        File file = (File) args[0];
        long chunkSize = (long) args[1];
        int maxConcurrency = (int) args[2];


        // 获取文件名和文件hash
        String fileId = md5Hex(file);
        String fileName = file.getName();

        // 获取已经上传到服务器中的分片文件信息
        Map<Integer, String> uploadChunkMap = uploadApi.uploadChunks(fileId);

        // 获取分片文件处理器进行处理
        FileChunkHandle handle = FileChunkHandle.of(file, chunkSize);
        handle.asyncHandle(
                sf -> {
                    if (isUploaded(uploadChunkMap, sf)) {
                        return Result.success("分片文件已上传");
                    } else {
                        return uploadApi.uploadChunk(fileId, sf.getIndex(), sf.getContent());
                    }
                },
                rs -> {
                    ResultClassify<Result<String>> classify = rs.classify((r -> r.getCode() == 200));
                    // 全部成功时调用合并接口进行数据合并
                    if (classify.isAllSuccess()) {
                        uploadApi.mergeChunks(fileId, fileName);
                        classify.successForEach(    r -> System.out.println(r.getResult().getData()));
                    } else {
                        classify.failureForEach(r -> {
                            FileChunk fileChunk = r.getFileChunk();
                            // 处理失败的结果
                            System.out.println(r.getResult().getMessage() + "->" + fileChunk);
                        });
                    }

                },
                maxConcurrency
        );
        return null;
    }

    @Override
    public boolean match(ExecuteMeta meta) {
        String methodName = meta.getMethod().getName();
        return FileChunkUploadApi.class == meta.getTargetClass()
                && Objects.equals(methodName, "uploadFile");
    }

    /**
     * 检查否个分片文件是否已经上传
     *
     * @param chunkMap  服务器上已有的分片文件信息
     * @param fileChunk 带检查的分片文件
     * @return 是否已经上传到服务器了
     */
    @SneakyThrows
    private boolean isUploaded(Map<Integer, String> chunkMap, @NonNull FileChunk fileChunk) {
        if (ContainerUtils.isEmptyMap(chunkMap)) {
            return false;
        }
        String hash = chunkMap.get((int) fileChunk.getIndex());
        if (hash == null) {
            return false;
        }

        return hash.equals(md5Hex(fileChunk.getContent()));
    }
}
