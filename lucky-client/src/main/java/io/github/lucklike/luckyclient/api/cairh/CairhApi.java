package io.github.lucklike.luckyclient.api.cairh;

import com.luckyframework.httpclient.proxy.annotations.Post;
import com.luckyframework.httpclient.proxy.annotations.PropertiesJsonObject;
import io.github.lucklike.httpclient.annotation.HttpClientComponent;
import io.github.lucklike.luckyclient.api.cairh.annotations.LooseBind;
import io.github.lucklike.luckyclient.api.cairh.response.QueryOperatorInfoResponse;

import java.util.Map;

@HttpClientComponent
public interface CairhApi extends BaseApi{

    @LooseBind
    @PropertiesJsonObject({"operator_no=#{operatorNo}"})
    @Post("/compfront/video/queryOperatorInfo")
    QueryOperatorInfoResponse queryOperatorInfo(String operatorNo);

    @PropertiesJsonObject({"productNo=#{productNo}"})
    @Post("/openapi/common/queryProduct")
    Map<String, Object> queryProduct(String productNo);
}
