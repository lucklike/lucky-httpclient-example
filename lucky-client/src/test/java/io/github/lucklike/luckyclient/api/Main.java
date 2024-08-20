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
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws Exception {
        Resource resource = ConversionUtils.conversion("classpath:test.yml", Resource.class);
        Reader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()));
        Map map = Resources.fromYamlReader(reader, Map.class);
        Map<String, Object> abc = (Map<String, Object>) map.get("abc");
        System.out.println(abc);
        A a = ConversionUtils.looseBind(A.class, abc);
        System.out.println(a);
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
