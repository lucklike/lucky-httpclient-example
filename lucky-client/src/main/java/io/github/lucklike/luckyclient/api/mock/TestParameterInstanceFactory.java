package io.github.lucklike.luckyclient.api.mock;

import com.luckyframework.httpclient.proxy.spel.ParameterInfo;
import com.luckyframework.reflect.AnnotationUtils;
import io.github.lucklike.httpclient.parameter.ParameterInstanceFactory;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

//@Component
public class TestParameterInstanceFactory implements ParameterInstanceFactory {
    @Override
    public boolean canCreateInstance(ParameterInfo parameterInfo) {
        return AnnotationUtils.isAnnotated(parameterInfo.getParameter(), NonNull.class);
    }

    @Override
    public Object createInstance(ParameterInfo parameterInfo) {
        return null;
    }
}
