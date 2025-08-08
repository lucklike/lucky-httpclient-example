package io.github.lucklike.luckyclient.api.cairh.htsec;

import com.luckyframework.common.ConfigurationMap;
import com.luckyframework.common.Table;
import com.luckyframework.httpclient.generalapi.AutoVerifyHttpStatus;
import com.luckyframework.httpclient.proxy.annotations.Post;
import com.luckyframework.httpclient.proxy.annotations.RespConvert;
import com.luckyframework.httpclient.proxy.annotations.StaticForm;
import com.luckyframework.httpclient.proxy.annotations.StaticHeader;
import com.luckyframework.httpclient.proxy.annotations.Wrapper;
import io.github.lucklike.httpclient.discovery.HttpClient;
import io.github.lucklike.luckyclient.api.cairh.htsec.resp.V2IdCardResult;

import java.util.Map;

//@HttpClient("http://ydbsorc.httest.cairenhui.com:9092")
@HttpClient("http://58.246.171.12:9999/api-debug/ocr_api/")
@StaticHeader("consumerCode:C5")
public interface HtqhOcrApi {

    @Post("/ocr/v2/id_card")
    @RespConvert("#{$body$.result[0]}")
    @StaticForm({"image_base64=#{#base64(#resource(idCard))}"})
    V2IdCardResult idCard(String idCard);

    @Wrapper("#{#id_card_info(new com.luckyframework.common.ConfigurationMap($this$.idCard(p0).recognize_result))}")
    String idInfo(String idCard);

    @Post("/ocr/v1/bank_card")
    @StaticForm({"image_base64=#{#base64(#resource(bankCard))}"})
    Map<String, Object> bankCard(String bankCard);

    @Wrapper("#{#id_card_info($this$.idCard(p0)?.result)}")
    String idCardInfo(String idCard);

    @Wrapper("#{#id_card_info($this$.bankCard(p0)?.result)}")
    String bankCardInfo(String bankCard);


    @SuppressWarnings("unchecked")
    static String id_card_info(ConfigurationMap resultMap) {
        Table table = new Table();
        table.addHeader("属性名", "属性值");

        for (Map.Entry<String, Object> entry : resultMap.entrySet()) {
            Object value = entry.getValue();
            if (value instanceof Map) {
                Map<String, Object> map = (Map<String, Object>) value;
                table.addDataRow(map.get("chinese_key"), map.get("words"));
            }
        }
        return table.format();
    }
}
