package io.github.lucklike.server.controller;

import io.github.lucklike.entity.response.Result;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;


@RestController
@RequestMapping("file")
public class FileController {


    @PostMapping("upload")
    public Result<String> upload(@RequestParam String id, MultipartFile[] file) throws IOException {
        System.out.println(id);
        StringBuilder sb = new StringBuilder();
        for (MultipartFile fileItem : file) {
            sb.append(fileItem.getOriginalFilename());
            sb.append(",");
            System.out.println(FileCopyUtils.copyToString(new InputStreamReader(fileItem.getInputStream())));
        }
        return Result.success(sb.toString());
    }
}
