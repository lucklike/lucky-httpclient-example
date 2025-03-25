package io.github.lucklike.luckyclient.api.deepseek.functioncalling.reequest;

import io.github.lucklike.common.api.annotation.ToolParam;
import lombok.Data;

@Data
public class TimelessQueryRequest {

    @ToolParam(desc = "歌曲名称，例如：泡沫")
    private String song;
}
