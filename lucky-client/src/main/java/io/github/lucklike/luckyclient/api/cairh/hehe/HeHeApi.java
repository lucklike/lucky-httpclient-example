package io.github.lucklike.luckyclient.api.cairh.hehe;

import com.luckyframework.common.ConfigurationMap;
import com.luckyframework.httpclient.proxy.annotations.BinaryBody;
import com.luckyframework.httpclient.proxy.annotations.FormParam;
import com.luckyframework.httpclient.proxy.annotations.Post;
import io.github.lucklike.httpclient.annotation.HttpClientComponent;
import io.github.lucklike.luckyclient.api.cairh.ApiName;
import io.github.lucklike.luckyclient.api.cairh.hehe.req.FaceCompareReq;
import io.github.lucklike.luckyclient.api.cairh.hehe.resp.HeheIdCardResponse;

@HttpClientComponent
public interface HeHeApi extends HeheBaseApi {


    @ApiName("ocr身份证识别")
    @Post("/icr/recognize_id_card")
    HeheIdCardResponse ocrIdCard(@BinaryBody String sfzPath);

    @ApiName("ocr人像对比")
    @Post("/face/similarity/image")
    ConfigurationMap ocrFaceCompare(@FormParam FaceCompareReq req);

}
