package io.github.lucklike.luckyclient.api.websocket.kdxf;

import cn.hutool.core.date.DateUtil;
import com.luckyframework.common.ConfigurationMap;
import com.luckyframework.httpclient.proxy.spel.FunctionAlias;
import com.luckyframework.httpclient.proxy.spel.Namespace;
import io.github.lucklike.luckyclient.api.cairh.BizException;

import java.util.Date;
import java.util.Map;

@Namespace("kdxf")
public class KdxfFunctions {

    //------------------------------------------------------------------------------------
    //                              Token Utils
    //------------------------------------------------------------------------------------


    @FunctionAlias("token")
    public static KdxfTokenManager.Token token(Map<String, Object> map) {
        ConfigurationMap config = new ConfigurationMap(map);
        return token(config.getString("data.access_token"), config.getLong("data.expire_time"));
    }

    @FunctionAlias("_token")
    public static KdxfTokenManager.Token token(String accessToken, long expires) {
        KdxfTokenManager.Token token = new KdxfTokenManager.Token();
        token.setAccessToken(accessToken);
        token.setExpires(DateUtil.offsetSecond(new Date(), (int) expires));
        return token;
    }

    //------------------------------------------------------------------------------------
    //                              Exception Utils
    //------------------------------------------------------------------------------------

    public static BizException statusError(int status, String msg) {
        return new BizException("【科大讯飞-ASR】获取Token接口调用失败, 异常的状态吗[{}],响应体信息：{}", status, msg);
    }

    public static BizException codeError(int code, String msg) {
        return new BizException("【科大讯飞-ASR】获取Token接口调用失败, code={}, msg={}", code, msg);
    }
}
