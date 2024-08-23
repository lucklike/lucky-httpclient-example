package io.github.lucklike.luckyclient.api.loosebind;

import com.luckyframework.httpclient.core.meta.Request;
import com.luckyframework.httpclient.proxy.configapi.RequestExtendHandle;
import com.luckyframework.httpclient.proxy.context.MethodContext;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class DefaultParamBindHandle implements RequestExtendHandle<BaseParam> {

    @Override
    public void handle(MethodContext context, Request request, BaseParam config) {
        for (Object argument : context.getArguments()) {
            if (argument instanceof BaseParam) {
                setDefaultParam((BaseParam) argument, config);
            }
        }
    }

    private void setDefaultParam(BaseParam param, BaseParam config) {
        BeanUtils.copyProperties(config, param);
    }
}
