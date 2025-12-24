package io.github.lucklike.luckyclient.api.yunxi;

import com.luckyframework.httpclient.generalapi.AutoVerifyHttpStatus;
import com.luckyframework.httpclient.generalapi.describe.Describe;
import com.luckyframework.httpclient.proxy.annotations.Condition;
import com.luckyframework.httpclient.proxy.annotations.Get;
import com.luckyframework.httpclient.proxy.annotations.HttpExec;
import com.luckyframework.httpclient.proxy.annotations.RespConvert;
import com.luckyframework.httpclient.proxy.annotations.StaticQuery;
import com.luckyframework.httpclient.proxy.annotations.UseProxy;
import com.luckyframework.httpclient.proxy.spel.SpelBean;
import com.luckyframework.spel.SimpleSpelBean;
import io.github.lucklike.httpclient.discovery.HttpClient;
import org.checkerframework.checker.units.qual.A;

/**
 * 云析API(http://api.zhyunxi.com)
 *
 * @author fukang
 * @version 1.0.0
 * @date 2025/12/5 00:26
 */
@YunXiClient
public interface YunXiApi {

    @Get("?api=18")
    @Describe("实时微博热榜")
//    @UseProxy(host = "61.158.175.38", port = "9002")
    @RespConvert("#{$body$.data[0].hot.![str('[{}][{}] {}', hot, title, url)]}")
    String[] weiboTop();

    /*
        47.96.143.117:80
        60.205.132.71:80
        221.231.13.198:1080
        183.215.23.242:9091
        47.93.121.200:80
        61.158.175.38:9002
     */
    @Get("?api=28")
    @Describe("获取代理ip")
    @RespConvert("#{$body$.data[0].list}")
    String[] getProxyIp();

    @Get("?api=28")
    @Describe("获取代理ip")
    SimpleSpelBean<?> getProxyIpSimpleSpelBean();

    @Get("?api=28")
    @Describe("获取代理ip")
    SpelBean<?> getProxyIpSpelBean();
}
