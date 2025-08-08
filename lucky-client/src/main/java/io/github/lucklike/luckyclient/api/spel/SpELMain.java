package io.github.lucklike.luckyclient.api.spel;

import com.luckyframework.httpclient.proxy.CommonFunctions;
import com.luckyframework.httpclient.proxy.spel.ClassStaticElement;
import com.luckyframework.spel.EvaluationContextFactory;
import com.luckyframework.spel.ParamWrapper;
import com.luckyframework.spel.SpELRuntime;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.expression.AccessException;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.MethodExecutor;
import org.springframework.expression.MethodResolver;
import org.springframework.expression.spel.support.ReflectiveMethodExecutor;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class SpELMain {

    public static void main(String[] args) {

        Map<String, Object> allStaticMethods = ClassStaticElement.create(CommonFunctions.class).getAllStaticMethods();

        SpELRuntime runtime = new SpELRuntime(new MyEvaluationContextFactory());
        ParamWrapper paramWrapper = new ParamWrapper();
        paramWrapper.setRootObject(Collections.singletonMap("lucky", allStaticMethods));

        Object valueForType = runtime.getValueForType(paramWrapper.setExpression("lucky.str('{} - {}', 'Hello', 'World!')"));
        System.out.println(valueForType);
    }

    static class MyEvaluationContextFactory implements EvaluationContextFactory {

        @Override
        public EvaluationContext getEvaluationContext(ParamWrapper paramWrapper) {
            StandardEvaluationContext evaluationContext = (StandardEvaluationContext) EvaluationContextFactory.DEFAULT_FACTORY.getEvaluationContext(paramWrapper);
            evaluationContext.addMethodResolver(new NamespaceMethodResolver());
            return evaluationContext;
        }
    }


    static class NamespaceMethodResolver implements MethodResolver {

        private final String DEFAULT_NAMESPACE = "lucky";

        @Override
        public MethodExecutor resolve(EvaluationContext context, Object targetObject, String name, List<TypeDescriptor> argumentTypes) throws AccessException {
            if (targetObject instanceof Map) {
                Map<?, ?> map = (Map<?, ?>) targetObject;
                Object def = map.get(DEFAULT_NAMESPACE);
                if (def instanceof Map) {
                    Map<?, ?> defMap = (Map<?, ?>) def;
                    Object o = defMap.get(name);
                    if (o instanceof Method) {
                        return new ReflectiveMethodExecutor(((Method) o));
                    }
                }
            }
            return null;
        }
    }
}
