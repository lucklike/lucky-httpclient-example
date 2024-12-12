package io.github.lucklike.luckyclient.api.cairh.comp;


import com.luckyframework.httpclient.generalapi.describe.Describe;
import com.luckyframework.httpclient.proxy.annotations.Post;
import com.luckyframework.httpclient.proxy.annotations.PropertiesJson;
import com.luckyframework.httpclient.proxy.annotations.RespConvert;
import com.luckyframework.httpclient.proxy.annotations.Wrapper;
import io.github.lucklike.luckyclient.api.cairh.annotations.CRHApi;
import io.github.lucklike.luckyclient.api.cairh.annotations.LooseBind;
import io.github.lucklike.luckyclient.api.cairh.comp.resp.QueryOperatorInfoResponse;

import java.util.concurrent.Future;

@CRHApi(project = "compfront")
public interface CompFrontApi {

    @Wrapper("#{$this$.queryOperatorInfo(operator_no).userName}")
    String getOperatorName(String operator_no);

    @LooseBind
    @PropertiesJson
    @RespConvert("#{$body$.data.user_name}")
    @Post("/video/queryOperatorInfo")
    Future<String> getOperatorName0(String operator_no);

    @LooseBind
    @Describe("操作员查询")
    @PropertiesJson
    @Post("/video/queryOperatorInfo")
    QueryOperatorInfoResponse queryOperatorInfo(String operator_no);
}
