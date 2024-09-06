package io.github.lucklike.luckyclient.api;

import com.luckyframework.common.ConfigurationMap;
import com.luckyframework.common.Resources;
import lombok.Data;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws Exception {
        ConfigurationMap configMap = Resources.resourceAsConfigMap("classpath:test.yml");
        A a = configMap.looseBindTo(A.class);
        System.out.println(a);

        B b = configMap.looseBind("b-map.b2", B.class);
        System.out.println(b);

        C c = configMap.looseBind("b-map.b2.map-map-list.key1.k1-1[0]", C.class);
        System.out.println(c);

        C[][] cc = configMap.looseBind("b-map.b2.c-list", C[][].class);
        System.out.println(Arrays.deepToString(cc));
    }

    @Data
    static class A {
        private String id;
        private String name;
        private Map<String, B> bMap;
    }

    @Data
    static class B {

        private String bId;
        private String bName;
        private List<List<C>> cList;
        private Map<String, Map<String, C[]>> mapMapList;
    }

    @Data
    static class C {

        private String cId;
        private String cName;

    }


}
