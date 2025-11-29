package io.github.lucklike.luckyclient.api.mainfun;

import com.luckyframework.reflect.ClassUtils;
import org.springframework.core.ResolvableType;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author fukang
 * @version 1.0.0
 * @date 2025/11/4 03:15
 */
public class MyMap<V> extends LinkedHashMap<String, V> {

    public static void main(String[] args) {
        MyMap<String> myMap  = new MyMap<>();
        myMap.put("a", "a");
        myMap.put("b", "b");
        ResolvableType myMapType = ClassUtils.getResolvableType(myMap);
        System.out.println(myMapType);

        MyMap<Integer> intMap = new MyMap<>();
        intMap.put("a", 1);
        intMap.put("b", 2);
        ResolvableType intMapType = ClassUtils.getResolvableType(intMap);
        System.out.println(intMapType);

        MyMap<List<MyMap<Integer>>> mmmp = new MyMap<>();
        mmmp.put("a", Arrays.asList(intMap));
        ResolvableType mmmpType = ClassUtils.getResolvableType(mmmp);
        System.out.println(mmmpType);
    }
}
