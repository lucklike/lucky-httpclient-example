package io.github.lucklike.server.controller;

import com.luckyframework.httpclient.proxy.annotations.HeaderParam;
import io.github.lucklike.server.ai.crh.ElecagreemodelApi;
import org.springframework.core.io.InputStreamSource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.Map;

@CrossOrigin
@RequestMapping("elecagreemodel")
@RestController
public class ElecagreemodelController {

    @Resource
    private ElecagreemodelApi api;

    @RequestMapping("fundSumaryPublish")
    public Map<String, Object> fundSumaryPublish(@RequestHeader("authorization") String token, @RequestParam String agreement_type, MultipartFile[] files) {
        Map<String, InputStreamSource> map = new LinkedHashMap<>();
        for (MultipartFile file : files) {
            map.put(file.getOriginalFilename(), file);
        }
        return api.fundSumaryPublish(token, agreement_type, map);
    }
}
