package io.github.lucklike.server.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import java.util.Comparator;

@RestController
@RequestMapping("/shard/upload")
public class ShardFileUploadController {

    private static final String UPLOAD_DIR = "D:/test/uploads/"; // 上传目录
    private static final String TEMP_DIR = "D:/test/uploads/temp/"; // 临时存储分片的目录

    // 初始化目录
    public ShardFileUploadController() throws IOException {
        Files.createDirectories(Paths.get(UPLOAD_DIR));
        Files.createDirectories(Paths.get(TEMP_DIR));
    }

    /**
     * 检查分片是否已存在
     */
    @GetMapping("/check")
    public ResponseEntity<Boolean> checkChunk(
            @RequestParam("fileId") String fileId,
            @RequestParam("chunkNumber") int chunkNumber
    ) {
        String chunkPath = TEMP_DIR + fileId + "/" + chunkNumber;
        File chunkFile = new File(chunkPath);
        return ResponseEntity.ok(chunkFile.exists());
    }

    /**
     * 上传分片文件
     */
    @PostMapping("/chunk")
    public ResponseEntity<String> uploadChunk(
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
                return ResponseEntity.ok("Chunk already exists"); // 分片已存在
            }

            file.transferTo(chunkFile); // 保存分片
            return ResponseEntity.ok("Chunk uploaded successfully");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Chunk upload failed");
        }
    }

    /**
     * 合并分片文件
     */
    @PostMapping("/merge")
    public ResponseEntity<String> mergeChunks(
            @RequestParam("fileId") String fileId,
            @RequestParam("fileName") String fileName
    ) {
        try {
            String chunkDir = TEMP_DIR + fileId;
            File[] chunks = new File(chunkDir).listFiles();

            if (chunks == null || chunks.length == 0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No chunks found");
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

            return ResponseEntity.ok("File merged successfully: " + fileName);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File merge failed");
        }
    }
}
