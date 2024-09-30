package io.github.lucklike.luckyclient.api.server.configapi;

import com.luckyframework.httpclient.core.meta.Request;
import com.luckyframework.httpclient.proxy.configapi.RequestExtendHandle;
import com.luckyframework.httpclient.proxy.context.MethodContext;
import com.luckyframework.httpclient.proxy.logging.RequestLogInfo;
import org.springframework.stereotype.Component;

@Component("myUserRequestExtendHandle")
public class UserRequestExtendHandle implements RequestExtendHandle<Object> {
    @Override
    public void handle(MethodContext context, Request request, Object config) {
        RequestLogInfo logInfo = new RequestLogInfo(request);
        System.out.println(logInfo.getBody());
    }
}
