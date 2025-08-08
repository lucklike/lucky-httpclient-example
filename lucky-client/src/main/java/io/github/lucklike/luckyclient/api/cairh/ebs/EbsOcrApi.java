package io.github.lucklike.luckyclient.api.cairh.ebs;

import com.luckyframework.httpclient.proxy.annotations.MultiFile;
import com.luckyframework.httpclient.proxy.annotations.Post;
import com.luckyframework.httpclient.proxy.annotations.StaticQuery;
import com.luckyframework.httpclient.proxy.spel.hook.callback.Var;
import io.github.lucklike.httpclient.discovery.HttpClient;

import static com.luckyframework.httpclient.proxy.spel.hook.Lifecycle.CLASS;

@HttpClient("#{$.url}")
@StaticQuery("app_key=#{$.appKey}")
public interface EbsOcrApi {

    @Var(lifecycle = CLASS)
    String $ = "#{env('cairh.ebs.ocr')}";

    @Post("/ebscn/ai.portal/v/image/ocr/recognize_id_card")
    String idCardParse(@MultiFile String cardPath);
}
