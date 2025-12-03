package io.github.lucklike.server.controller;

import com.luckyframework.httpclient.proxy.function.DigestFunctions;
import io.github.lucklike.entity.response.Result;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/shard/upload")
public class FileChunkUploadController {

    private static final String UPLOAD_DIR = "D:/test/shard/uploads/"; // 上传目录
    private static final String TEMP_DIR = "D:/test/shard/uploads/temp/"; // 临时存储分片的目录

    // 初始化目录
    public FileChunkUploadController() throws IOException {
        Files.createDirectories(Paths.get(UPLOAD_DIR));
        Files.createDirectories(Paths.get(TEMP_DIR));
    }

    /**
     * 获取已经上传的文件列表
     *
     * @param fileId 文件ID
     * @return 已经上传的文件列表
     */
    @GetMapping("uploadChunks")
    public Result<Map<Integer, String>> uploadChunks(@RequestParam("fileId") String fileId) {
        String chunkDir = TEMP_DIR + fileId;
        File[] chunks = new File(chunkDir).listFiles();
        if (chunks == null) {
            return Result.success(Collections.emptyMap());
        }
        Map<Integer, String> chunkMap = Stream.of(chunks)
                .collect(Collectors.toMap(f -> Integer.parseInt(f.getName()), this::fileHash));
        return Result.success(chunkMap);
    }

    /**
     * 检查分片是否已存在
     */
    @GetMapping("/check")
    public Result<Boolean> checkChunk(
            @RequestParam("fileId") String fileId,
            @RequestParam("chunkNumber") int chunkNumber
    ) {
        String chunkPath = TEMP_DIR + fileId + "/" + chunkNumber;
        File chunkFile = new File(chunkPath);
        return Result.success(chunkFile.exists());
    }

    /**
     * 上传分片文件
     */
    @PostMapping("/chunk")
    public Result<String> uploadChunk(
            @RequestParam("fileId") String fileId,
            @RequestParam("chunkNumber") int chunkNumber,
            @RequestParam("file") MultipartFile file
    ) {
        try {
            // 保存分片
            String chunkDir = TEMP_DIR + fileId;
            Files.createDirectories(Paths.get(chunkDir)); // 创建分片目录

            String chunkFilePath = chunkDir + "/" + chunkNumber;
            File chunkFile = new File(chunkFilePath);
            if (chunkFile.exists()) {
                return Result.fail(100,"分片已存在"); // 分片已存在
            }

            file.transferTo(chunkFile); // 保存分片
            return Result.success("分片上传成功");
        } catch (IOException e) {
            return Result.fail(500, "分片上传失败");
        }
    }

    /**
     * 合并分片文件
     */
    @PostMapping("/merge")
    public Result<String> mergeChunks(
            @RequestParam("fileId") String fileId,
            @RequestParam("fileName") String fileName
    ) {
        try {
            String chunkDir = TEMP_DIR + fileId;
            File[] chunks = new File(chunkDir).listFiles();

            if (chunks == null || chunks.length == 0) {
                return Result.fail(100, "为找到分片文件");
            }

            // 按文件序号排序
            Arrays.sort(chunks, Comparator.comparingInt(
                    file -> Integer.parseInt(file.getName())
            ));

            // 合并文件
            File outputFile = new File(UPLOAD_DIR + fileName);
            try (FileOutputStream out = new FileOutputStream(outputFile, true)) {
                for (File chunk : chunks) {
                    Files.copy(chunk.toPath(), out);
                }
            }

            // 删除分片目录
            for (File chunk : chunks) {
                chunk.delete();
            }
            new File(chunkDir).delete();

            return Result.success("文件合并成功");
        } catch (IOException e) {
            return Result.fail(500, "文件合并失败");
        }
    }

    @SneakyThrows
    private String fileHash(File file) {
        return DigestFunctions.md5Hex(file);
    }
}
