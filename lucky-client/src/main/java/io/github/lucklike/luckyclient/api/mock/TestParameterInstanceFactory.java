package io.github.lucklike.luckyclient.api.mock;

import com.luckyframework.httpclient.proxy.spel.ParameterInfo;
import io.github.lucklike.httpclient.injection.parameter.AnnotationParameterInstanceFactory;
import org.springframework.core.ResolvableType;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

//@Component
public class TestParameterInstanceFactory extends AnnotationParameterInstanceFactory<Nullable> {
    @Override
    protected Object doCreateInstance(ParameterInfo parameterInfo, ResolvableType realType, Nullable annotation) {
        return null;
    }
}
