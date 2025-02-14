package io.github.lucklike.luckyclient.api.cairh.ttd;

import com.luckyframework.common.ConfigurationMap;
import com.luckyframework.httpclient.proxy.annotations.Post;
import io.github.lucklike.luckyclient.api.cairh.ttd.resp.TtdResponse;

import java.util.Map;

@TTDClient
public interface TTDApi {

    @Post("/ttd-api-service/product/list")
    TtdResponse<Map<String, Object>> productList(Integer pageNum, Integer pageSize);


    @Post("/ttd-api-service/get/token")
    String getAccessToken();

}
