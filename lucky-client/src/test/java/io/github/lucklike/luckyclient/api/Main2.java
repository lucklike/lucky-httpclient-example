package io.github.lucklike.luckyclient.api;

import com.luckyframework.common.CtrlMap;
import com.luckyframework.httpclient.proxy.spel.InternalParamName;
import com.luckyframework.httpclient.proxy.spel.ProhibitCoverEnum;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;

public class Main2 {

    public static void main(String[] args) throws URISyntaxException {
        CtrlMap<String, Object> ctrlMap = new CtrlMap<>(
                new LinkedHashMap<>(),
                key -> !InternalParamName.getAllInternalParamName().contains(key),
                key -> !ProhibitCoverEnum.isMatch(key)
        );

        ctrlMap.put("a", 123);
        ctrlMap.put("$b", 456);
        ctrlMap.put("$b", 789);
        ctrlMap.put(InternalParamName.$_METHOD_$, "huihui");

        for (Map.Entry<String, Object> entry : ctrlMap.entrySet()) {
            Object value = entry.getValue();
            entry.setValue(value + "update");
        }

        ctrlMap.forEach((k, v) -> System.out.println(k + ": " + v));
    }

}
