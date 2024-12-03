package io.github.lucklike.luckyclient.api.mainfun;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.luckyframework.serializable.JsonSerializationScheme;
import com.luckyframework.serializable.SerializationSchemeFactory;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.LinkedHashMap;
import java.util.Map;


@Data
public class TestMap {

    private String fun;

    private String fun2;

    @JsonUnwrapped
    private Map<String, Object> map;


    public static void main(String[] args) throws Exception {
        TestMap tMap = new TestMap();
        tMap.setFun("test");
        tMap.setFun2("test2");

        Map<String, Object> map = new LinkedHashMap<>();
        map.put("a1", "test");
        map.put("a2", "test2");
        tMap.setMap(map);


        JsonSerializationScheme jsonScheme = SerializationSchemeFactory.getJsonScheme();
        System.out.println(jsonScheme.serialization(tMap));

//        TestMap test = (TestMap) jsonScheme.deserialization("{\"sdd\":\"fewfwe\", \"fun\":\"dsdsd\"}", TestMap.class);
//        System.out.println(test);
    }

}
