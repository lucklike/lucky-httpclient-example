package io.github.lucklike.luckyclient.api;

import com.luckyframework.reflect.ASMUtil;
import com.luckyframework.reflect.ClassUtils;
import io.github.lucklike.luckyclient.api.spark.SparkCompletionsEventListener;
import io.github.lucklike.luckyclient.doc.LuckyApi;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.stream.Stream;

public class Main1 {


    public static void main(String[] args) throws IOException {
        methodTest();
    }

    private static void methodTest() {
        Method[] declaredMethodOrder = ASMUtil.getAllMethodOrder(LuckyApi.class);
        Stream.of(declaredMethodOrder).forEach(m -> System.out.println(m));

        System.out.println("-------------------------------------");

        Method[] declaredMethodOrder2 = ClassUtils.getAllMethod(LuckyApi.class);
        Stream.of(declaredMethodOrder2).forEach(m -> System.out.println(m));
    }

    private static void fieldTest() {
        Field[] declaredMethodOrder = ASMUtil.getAllFieldsOrder(SparkCompletionsEventListener.class);
        Stream.of(declaredMethodOrder).forEach(m -> System.out.println(m.getName()));

        System.out.println("-------------------------------------");

        Field[] declaredMethodOrder2 = ClassUtils.getAllFields(SparkCompletionsEventListener.class);
        Stream.of(declaredMethodOrder2).forEach(m -> System.out.println(m.getName()));
    }
}
