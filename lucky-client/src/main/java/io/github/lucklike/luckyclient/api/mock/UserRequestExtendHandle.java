package io.github.lucklike.luckyclient.api.mock;

import com.luckyframework.common.Console;
import com.luckyframework.httpclient.core.meta.Request;
import com.luckyframework.httpclient.proxy.configapi.RequestExtendHandle;
import com.luckyframework.httpclient.proxy.context.MethodContext;
import org.springframework.stereotype.Component;

/**
 * @author fukang
 * @version 1.0.0
 * @date 2024/8/18 23:31
 */
@Component
public class UserRequestExtendHandle implements RequestExtendHandle<User> {

    @Override
    public void handle(MethodContext context, Request request, User config) {
        Console.println("{} -> {}", config.getClass(), config);
    }
}
