package io.github.lucklike.luckyclient.api.cairh.ttd;

import com.luckyframework.common.FlatBean;
import com.luckyframework.httpclient.generalapi.token.UseTokenManager;
import com.luckyframework.httpclient.proxy.annotations.Post;
import com.luckyframework.httpclient.proxy.annotations.RespConvert;
import com.luckyframework.httpclient.proxy.spel.SpelBean;
import com.luckyframework.spel.SimpleSpelBean;
import io.github.lucklike.luckyclient.api.cairh.ttd.ann.TTDClient;
import io.github.lucklike.luckyclient.api.cairh.ttd.ann.TTDJForm;
import io.github.lucklike.luckyclient.api.cairh.ttd.req.Page;
import io.github.lucklike.luckyclient.api.cairh.ttd.resp.Product;
import io.github.lucklike.luckyclient.api.cairh.ttd.resp.Token;

import java.util.List;

@TTDClient
public interface TTDApi {

    @RespConvert("#{$ttd_data.list}")
    @Post("/ttd-api-service/product/list")
    List<Product> productList(@TTDJForm Page page);

    @RespConvert("#{$ttd_data.list}")
    @Post("/ttd-api-service/product/list")
    FlatBean<?> productListFlatBean(@TTDJForm Page page);

    @RespConvert("#{$ttd_data.list}")
    @Post("/ttd-api-service/product/list")
    SimpleSpelBean<?> productListSimpleSpelBean(@TTDJForm Page page);

    @RespConvert("#{$ttd_data.list}")
    @Post("/ttd-api-service/product/list")
    SpelBean<?> productListSpelBean(@TTDJForm Page page);

    @RespConvert("#{$ttd_data.bean}")
    @Post("/ttd-api-service/get/token")
    @UseTokenManager("${user.dir}/ttd-token.json")
    Token token();

    @RespConvert("#{$ttd_data.list.![_json(revealBookUrl).url]}")
    @Post("/ttd-api-service/product/list")
    List<String> getWatermarkContractSignUrls(@TTDJForm Page page);

}
