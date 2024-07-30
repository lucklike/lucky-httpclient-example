package io.github.lucklike.luckyclient.util;

import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class OSProperties implements Map<String, String> {

    private final Map<String, String> windowsMap = new HashMap<String, String>(){{
        put("fileSavePath", "D:/test/");
        put("resources", "D:/Lucky/lucky-httpclient-example/lucky-client/src/main/resources/");
    }};

    private final Map<String, String> macMap = new HashMap<String, String>(){{
        put("fileSavePath", "/test/");
        put("resources", "/Users/fukang/Lucky/lucky-httpclient-example/lucky-client/src/main/resources/");
    }};

    private final Map<String, String> finalMap;

    {
        String osName = System.getProperty("os.name");
        if (osName.toLowerCase().contains("windows")) {
            finalMap = windowsMap;
        } else {
            finalMap = macMap;
        }
    }


    @Override
    public int size() {
        return finalMap.size();
    }

    @Override
    public boolean isEmpty() {
        return finalMap.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return finalMap.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return finalMap.containsValue(value);
    }

    @Override
    public String get(Object key) {
        return finalMap.get(key);
    }

    @Override
    public String put(String key, String value) {
        return finalMap.put(key, value);
    }

    @Override
    public String remove(Object key) {
        return finalMap.remove(key);
    }

    @Override
    public void putAll(Map<? extends String, ? extends String> m) {
        finalMap.putAll(m);
    }

    @Override
    public void clear() {
        finalMap.clear();
    }

    @Override
    public Set<String> keySet() {
        return finalMap.keySet();
    }

    @Override
    public Collection<String> values() {
        return finalMap.values();
    }

    @Override
    public Set<Entry<String, String>> entrySet() {
        return finalMap.entrySet();
    }
}
