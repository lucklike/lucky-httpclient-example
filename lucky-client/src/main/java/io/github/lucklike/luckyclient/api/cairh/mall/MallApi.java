package io.github.lucklike.luckyclient.api.cairh.mall;

import com.luckyframework.common.ConfigurationMap;
import com.luckyframework.httpclient.proxy.annotations.Get;
import com.luckyframework.httpclient.proxy.annotations.QueryParam;
import com.luckyframework.httpclient.proxy.annotations.RespConvert;
import com.luckyframework.httpclient.proxy.annotations.Retryable;
import com.luckyframework.httpclient.proxy.spel.SpELImport;
import io.github.lucklike.httpclient.annotation.HttpClient;

import java.util.List;
import java.util.Set;

@SpELImport(root = {"_v001_=CRH-MALLV001", "_v002_=CRH-MALLV002"})
@Retryable
@RespConvert("#{$body$.resultList}")
@HttpClient("${cairh.maill.url}")
public interface MallApi {

    @Get("#{_v001_}")
    List<ConfigurationMap> getAllSeries(@QueryParam("page_size") Integer pageSize);

    @Get("#{_v002_}")
    List<ConfigurationMap> getProdList(@QueryParam("record_no") String record_no);

    @RespConvert("#{$body$.resultList.!['【' + serial_no + '】' + record_name]}")
    @Get("#{_v001_}?page_size=69")
    Set<String> getAllSeriesId();
}
