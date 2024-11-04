package io.github.lucklike.luckyclient.api.server.fuse;

import cn.hutool.core.date.DateUtil;
import com.luckyframework.common.ExceptionUtils;
import com.luckyframework.httpclient.core.meta.Request;
import com.luckyframework.httpclient.proxy.context.MethodContext;
import com.luckyframework.httpclient.proxy.fuse.FuseProtector;
import lombok.extern.slf4j.Slf4j;

import java.net.ConnectException;
import java.util.Date;
import java.util.Hashtable;
import java.util.Map;

/**
 * @author fukang
 * @version 1.0.0
 * @date 2024/11/4 03:04
 */
@Slf4j
public class MyFuse implements FuseProtector {

    private final Map<String, Date> fuseConfigMap = new Hashtable<>();


    @Override
    public boolean fuseOrNot(MethodContext methodContext, Request request) {
        return fuseCheck(request);
    }

    @Override
    public void recordFailure(MethodContext methodContext, Request request, Throwable throwable) {
        Throwable causeThrowable = ExceptionUtils.getCauseThrowable(throwable);
        if (causeThrowable instanceof ConnectException) {
            Date date = DateUtil.offsetSecond(new Date(), 20);
            fuseConfigMap.put(getKey(request), date);
        }
    }

    @Override
    public void recordSuccess(MethodContext methodContext, Request request, long timeConsuming) {
        System.out.println(timeConsuming);
    }


    private String getKey(Request request) {
        return String.format("%s:%s", request.getURL().getHost(), request.getURL().getPort());
    }

    private boolean fuseCheck(Request request) {
        String key = getKey(request);
        Date date = fuseConfigMap.get(key);
        if (date == null) {
            return false;
        }
        if (date.after(new Date())) {
            log.info("主动熔断当前请求：{}", request.getUrl());
            return true;
        } else {
            fuseConfigMap.remove(key);
            return false;
        }
    }
}
