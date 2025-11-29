package io.github.lucklike.luckyclient.api.cairh.comp;


import com.luckyframework.httpclient.core.meta.Response;
import com.luckyframework.httpclient.generalapi.describe.Describe;
import com.luckyframework.httpclient.proxy.annotations.JsonParam;
import com.luckyframework.httpclient.proxy.annotations.Post;
import com.luckyframework.httpclient.proxy.annotations.PrintLogProhibition;
import com.luckyframework.httpclient.proxy.annotations.Wrapper;
import io.github.lucklike.luckyclient.api.cairh.annotations.CRHApi;
import io.github.lucklike.luckyclient.api.cairh.annotations.LooseBind;
import io.github.lucklike.luckyclient.api.cairh.comp.resp.QueryOperatorInfoResponse;

import java.util.concurrent.Future;

@PrintLogProhibition
@CRHApi(project = "compfront")
public interface CompFrontApi {

    @Wrapper("#{$this$.queryOperatorInfo(operator_no).userName}")
    Future<String> getOperatorName(String operator_no);

    @Wrapper("#{#lb($mc$, $this$.operatorInfo(operator_no).getEntity(T(Object)).data)}")
    QueryOperatorInfoResponse queryOperatorInfo(@JsonParam String operator_no);

    @LooseBind
    @Describe("操作员查询")
    @Post("/video/queryOperatorInfo")
    QueryOperatorInfoResponse queryOperatorInfo0(@JsonParam String operator_no);

    @Describe("操作员查询")
    @Post("/video/queryOperatorInfo")
    Response operatorInfo(@JsonParam String operator_no);
}
