package io.github.lucklike.luckyclient.api.deepseek.functioncalling.reequest;

import io.github.lucklike.common.api.annotation.ToolParam;
import lombok.Data;

@Data
public class OilPriceQueryRequest {

    @ToolParam(desc = "城市信息，例如：湖北")
    private String province;
}
