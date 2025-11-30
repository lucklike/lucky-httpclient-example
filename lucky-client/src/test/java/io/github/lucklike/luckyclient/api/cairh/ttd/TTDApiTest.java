package io.github.lucklike.luckyclient.api.cairh.ttd;

import com.luckyframework.common.Console;
import com.luckyframework.common.FlatBean;
import com.luckyframework.httpclient.generalapi.download.FileApi;
import com.luckyframework.httpclient.proxy.spel.SpelBean;
import com.luckyframework.io.MultipartFile;
import io.github.lucklike.httpclient.injection.HttpReference;
import io.github.lucklike.luckyclient.api.cairh.ttd.req.Page;
import io.github.lucklike.luckyclient.api.cairh.ttd.resp.Product;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static com.luckyframework.httpclient.proxy.CommonFunctions._url;

@SpringBootTest
class TTDApiTest {

    @Resource
    private TTDApi ttdApi;

    @HttpReference
    private FileApi fileApi;

    @Test
    void productList() {
        List<Product> productList = ttdApi.productList(Page.of(1, 10));
        System.out.println(productList);
        FlatBean<?> flatBean = FlatBean.of(productList);
    }


    @Test
    void productListFlatBean() {
        FlatBean<?> flatBean = ttdApi.productListFlatBean(Page.of(1, 10));
        System.out.println(flatBean.getString("[5].contractUrl"));
        System.out.println(flatBean.tryGetString("[5].contractUrl$Map.url"));
    }

    @Test
    void productListSpelBean() {
        SpelBean<?> spelBean = ttdApi.productListSpelBean(Page.of(1, 10));
        System.out.println(spelBean.getString("$bean$[5].contractUrl"));
        System.out.println(spelBean.getString("$bean$[5].contractUrl2?.ok"));

        System.out.println(spelBean.getString("_json($bean$[0].watermarkContractSignUrl).url"));
        String[] urls = spelBean.getStringArray("$bean$.![_json(contractUrl).objectKey]");
        Stream.of(urls).forEach(System.out::println);
//        System.out.println(spelbean.tryGetString("[5].contractUrl$Map.url"));
    }


    @Test
    void getAccessToken() {
        System.out.println(ttdApi.getAccessToken());
    }

    @Test
    void getWatermarkContractSignUrlsTest() throws IOException {
        List<String> watermarkContractSignUrls = ttdApi.getWatermarkContractSignUrls(Page.of(1, 10));
        for (String url : watermarkContractSignUrls) {
            MultipartFile file = fileApi.getFile(url);
            String originalFileName = file.getOriginalFileName();
            file.setFileName(_url(originalFileName));
            file.copyToFolder("/Users/fukang/Desktop/test/ttd/");
        }
        System.out.println(watermarkContractSignUrls);
    }
}