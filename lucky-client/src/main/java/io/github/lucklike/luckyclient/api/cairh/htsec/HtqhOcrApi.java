package io.github.lucklike.luckyclient.api.cairh.htsec;

import com.luckyframework.httpclient.proxy.annotations.Post;
import com.luckyframework.httpclient.proxy.annotations.StaticForm;
import com.luckyframework.httpclient.proxy.annotations.StaticHeader;
import io.github.lucklike.httpclient.discovery.HttpClient;

import java.util.Map;

//@HttpClient("http://ydbsorc.httest.cairenhui.com:9092")
@HttpClient("http://58.246.171.12:9999/api-debug/ocr_api/")
@StaticForm("consumerCode=C5")
public interface HtqhOcrApi {

    @Post("/ocr/v1/id_card")
    @StaticForm({"image_base64=#{#base64(#resource(idCard))}"})
    Map<String, Object> idCard(String idCard);

    @Post("/ocr/v1/bank_card")
    @StaticForm({"image_base64=#{#base64(#resource(bankCard))}"})
    Map<String, Object> bankCard(String bankCard);
}
