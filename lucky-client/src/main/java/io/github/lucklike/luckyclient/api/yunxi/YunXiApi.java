package io.github.lucklike.luckyclient.api.yunxi;

import com.luckyframework.httpclient.generalapi.AutoVerifyHttpStatus;
import com.luckyframework.httpclient.generalapi.describe.Describe;
import com.luckyframework.httpclient.proxy.annotations.Condition;
import com.luckyframework.httpclient.proxy.annotations.Get;
import com.luckyframework.httpclient.proxy.annotations.RespConvert;
import com.luckyframework.httpclient.proxy.annotations.StaticQuery;
import com.luckyframework.httpclient.proxy.spel.SpelBean;
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
    @RespConvert("#{$body$.data[0].hot.![str('[{}][{}] {}', hot, title, url)]}")
    @Describe("实时微博热榜")
    String[] weiboTop();
}
