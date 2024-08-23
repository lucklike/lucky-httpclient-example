package io.github.lucklike.luckyclient.api.loosebind;

import com.luckyframework.httpclient.proxy.annotations.StaticParam;
import com.luckyframework.reflect.AnnotationUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.lang.annotation.Annotation;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LooseBindApiTest {

    @Resource
    private LooseBindApi looseBindApi;

    @Resource
    private DefaultParamBindApi defaultParamBindApi;

    @Test
    void book() {
        System.out.println(looseBindApi.book());
    }

    @Test
    void bindTest() {
        System.out.println(defaultParamBindApi.test(new BaseParam()));
    }

    public static void main(String[] args) {
        Set<Annotation> annotationSet = AnnotationUtils.getContainCombinationAnnotationsIgnoreSource(DefaultParamBindApi.class, StaticParam.class);
        System.out.println(annotationSet);
    }
}