package io.github.lucklike.server.controller;

import io.github.lucklike.entity.response.Result;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
@RequestMapping("file")
public class FileController {


    @PostMapping("upload")
    public Result<String> upload(@RequestParam String id, MultipartFile file) throws IOException {
        System.out.println(file.getOriginalFilename());
        return Result.success(new String(FileCopyUtils.copyToByteArray(file.getInputStream())));
    }
}
