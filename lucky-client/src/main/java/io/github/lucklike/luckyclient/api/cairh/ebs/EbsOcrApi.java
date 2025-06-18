package io.github.lucklike.luckyclient.api.cairh.ebs;

import com.luckyframework.httpclient.proxy.annotations.MultiFile;
import com.luckyframework.httpclient.proxy.annotations.Post;
import com.luckyframework.httpclient.proxy.annotations.StaticQuery;
import io.github.lucklike.httpclient.discovery.HttpClient;

@StaticQuery("app_key=${cairh.ebs.ocr.appKey}")
@HttpClient("${cairh.ebs.ocr.url}")
public interface EbsOcrApi {

    @Post("ebscn/ai.portal/v/image/ocr/recognize_id_card")
    String idCardParse(@MultiFile String cardPath);
}
