package io.github.lucklike.luckyclient.api.deepseek.functioncalling.lserver;

import io.github.lucklike.common.api.annotation.FunctionCalling;
import io.github.lucklike.common.api.annotation.Tool;
import io.github.lucklike.luckyclient.api.deepseek.functioncalling.reequest.OilPriceQueryRequest;
import io.github.lucklike.luckyclient.api.roll.CurrentLimitationOilPriceApi;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

@Component
@FunctionCalling
public class OilPriceQueryApi {

    @Resource
    private CurrentLimitationOilPriceApi currentLimitationOilPriceApi;

    @Tool(name = "oil_price_query", desc = "石油价格查询接口，用户应提供一个城市", required = "province")
    public Map<String, Object> limitQuery(@NonNull OilPriceQueryRequest provinceReq) {
        return currentLimitationOilPriceApi.limitQuery(provinceReq.getProvince());
    }
}
