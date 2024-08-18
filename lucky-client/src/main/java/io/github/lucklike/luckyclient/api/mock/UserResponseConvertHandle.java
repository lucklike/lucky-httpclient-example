package io.github.lucklike.luckyclient.api.mock;

import com.luckyframework.common.StringUtils;
import com.luckyframework.httpclient.core.meta.Response;
import com.luckyframework.httpclient.proxy.configapi.ResponseConvertHandle;
import com.luckyframework.httpclient.proxy.context.MethodContext;
import org.springframework.stereotype.Component;

/**
 * @author fukang
 * @version 1.0.0
 * @date 2024/8/18 23:31
 */
@Component
public class UserResponseConvertHandle implements ResponseConvertHandle<String, User> {

    @Override
    public String handle(MethodContext context, Response response, User config) {
        return StringUtils.format("[{}] {} \nconfig: {} \nResponse: {}",
                response.getStatus(),
                response.getRequest().getUrl(),
                config,
                response.getStringResult()
        );
    }
}
