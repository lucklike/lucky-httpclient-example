package io.github.lucklike.luckyclient.api.deepseek.functioncalling.lserver;

import io.github.lucklike.common.api.annotation.FunctionCalling;
import io.github.lucklike.common.api.annotation.Tool;
import io.github.lucklike.luckyclient.api.deepseek.functioncalling.reequest.TimelessQueryRequest;
import io.github.lucklike.luckyclient.api.timeless.TimelessApi;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@FunctionCalling
public class TimelessQueryApi {

    @Resource
    private TimelessApi timelessApi;

    @Tool(name = "qry_lyrics", desc = "歌词查询接口，输入歌名返回歌词", required = "song")
    public String qryLyricsFindCache(TimelessQueryRequest request) {
        return timelessApi.qryLyricsFindCache(request.getSong());
    }
}
