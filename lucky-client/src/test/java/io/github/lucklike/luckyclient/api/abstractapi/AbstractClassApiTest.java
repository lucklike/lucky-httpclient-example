package io.github.lucklike.luckyclient.api.abstractapi;

import com.luckyframework.reflect.AnnotationUtils;
import com.luckyframework.reflect.MethodUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

@SpringBootTest
class AbstractClassApiTest {

    @Resource
    private AbstractClassApi abstractClassApi;

    @Test
    void meagerResult() {
        Map<String, Object> meagerResult = abstractClassApi.meagerResult();
        System.out.println(meagerResult);
    }

    public static void main(String[] args) {
        Method method = MethodUtils.getDeclaredMethod(AbstractClassApi.class, "baidu");
        List<Annotation> combinationAnnotations = AnnotationUtils.getCombinationAnnotations(method);
        for (Annotation combinationAnnotation : combinationAnnotations) {
            System.out.println(combinationAnnotation);
        }
    }
}