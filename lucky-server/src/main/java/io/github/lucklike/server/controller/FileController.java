package io.github.lucklike.server.controller;

import io.github.lucklike.entity.response.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("file")
public class FileController {


    @PostMapping("upload")
    public Result<String> upload(@RequestParam String id, MultipartFile file) {
        System.out.println(file.getOriginalFilename());
        return Result.success(file.getOriginalFilename());
    }
}
