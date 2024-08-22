package io.github.lucklike.luckyclient.api;

import com.luckyframework.common.ConfigurationMap;
import com.luckyframework.common.Resources;
import com.luckyframework.conversion.ConversionUtils;
import com.luckyframework.httpclient.proxy.configapi.CommonApi;
import com.luckyframework.httpclient.proxy.configapi.LocalFileConfigurationSource;
import com.luckyframework.loosebind.FieldInvalidException;
import com.luckyframework.loosebind.FieldUnknownException;
import com.luckyframework.loosebind.LooseBind;
import lombok.Data;
import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
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
