package io.github.lucklike.server.controller;

import io.github.lucklike.server.ai.crh.ElecagreemodelApi;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.Map;

@CrossOrigin
@RequestMapping("elecagreemodel")
@RestController
public class ElecagreemodelController {

    @Resource
    private ElecagreemodelApi api;

    @RequestMapping("fundSumaryPublish")
    public Map<String, Object> fundSumaryPublish(@RequestHeader("authorization") String token, @RequestParam String agreement_type, MultipartFile[] files) {
        return api.fundSumaryPublish(token, agreement_type, files);
    }
}
