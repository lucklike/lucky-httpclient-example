package io.github.lucklike.luckyclient.api.cairh.hehe;

import com.luckyframework.common.ConfigurationMap;
import com.luckyframework.httpclient.generalapi.describe.Describe;
import com.luckyframework.httpclient.proxy.annotations.BinaryBody;
import com.luckyframework.httpclient.proxy.annotations.FormParam;
import com.luckyframework.httpclient.proxy.annotations.Post;
import io.github.lucklike.httpclient.annotation.HttpClientComponent;
import io.github.lucklike.luckyclient.api.cairh.hehe.req.FaceCompareReq;
import io.github.lucklike.luckyclient.api.cairh.hehe.resp.BankCardResponse;
import io.github.lucklike.luckyclient.api.cairh.hehe.resp.BusinessLicenseResponse;
import io.github.lucklike.luckyclient.api.cairh.hehe.resp.GATResidenceResponse;
import io.github.lucklike.luckyclient.api.cairh.hehe.resp.HeheIdCardResponse;

import java.util.Map;

@HttpClientComponent
public interface HeHeApi extends HeheBaseApi {

    @Describe("合合OCR身份证识别")
    @Post("/icr/recognize_id_card")
    HeheIdCardResponse ocrIdCard(@BinaryBody String sfzPath);

    @Describe("合合OCR银行卡识别")
    @Post("/icr/recognize_bank_card")
    BankCardResponse orrBankCard(@BinaryBody String bankPath);

    @Describe("合合OCR营业执照识别")
    @Post("/icr/recognize_biz_license")
    BusinessLicenseResponse ocrBusinessLicense(@BinaryBody String bizLicensePath);

    @Describe("合合OCR港澳台通行证识别")
    @Post("/icr/recognize_taiwan_compatriot")
    GATResidenceResponse ocrGATResidence(@BinaryBody String gatPath);

    @Describe("合合OCR永居证识别")
    @Post("/ai/service/v1/foreign_permanent_resident_id_card")
    ConfigurationMap ocrPermanentResident(@BinaryBody String permanentPath);

    @Describe(id = "ycv1", name = "OCR人像对比")
    @Post("/face/similarity/image")
    ConfigurationMap ocrFaceCompare(@FormParam FaceCompareReq req);

}
