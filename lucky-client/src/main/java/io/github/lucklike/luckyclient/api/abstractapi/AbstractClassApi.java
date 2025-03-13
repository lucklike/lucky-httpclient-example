package io.github.lucklike.luckyclient.api.abstractapi;

import com.luckyframework.httpclient.proxy.annotations.Get;
import io.github.lucklike.httpclient.discovery.HttpClient;
import io.github.lucklike.luckyclient.api.roll.OilPriceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;


@HttpClient
public abstract class AbstractClassApi {

    @Autowired
    private OilPriceApi oilPriceApi;

    @Value("${lucky-server.http}")
    private String luckyServerUrl;

    @Get("http://www.baidu.com")
    abstract String baidu();

    public Map<String, Object> meagerResult() {
        Map<String, Object> result = new HashMap<>();
        String baidu = baidu();
        Map<String, Object> oilPriceMap = oilPriceApi.query("湖北");
        result.put("百度", baidu);
        result.put("湖北油价", oilPriceMap);
        result.put("Lucky服务地址", luckyServerUrl);
        return result;
    }
}
