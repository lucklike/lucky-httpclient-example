package io.github.lucklike.luckyclient.api;

import com.luckyframework.common.ConfigurationMap;
import com.luckyframework.httpclient.proxy.configapi.CommonApi;
import com.luckyframework.httpclient.proxy.configapi.LocalFileConfigurationSource;
import com.luckyframework.loosebind.FieldInvalidException;
import com.luckyframework.loosebind.FieldUnknownException;
import com.luckyframework.loosebind.LooseBind;

public class Main {
    public static void main(String[] args) throws FieldUnknownException, FieldInvalidException {
        LocalFileConfigurationSource source = new LocalFileConfigurationSource();
        ConfigurationMap configMap = source.getConfigMap("classpath:api/mock/MockApi.yml", "io.github.lucklike.luckyclient.api.mock.MockApi");
        CommonApi api = new CommonApi();
        LooseBind looseBind = new LooseBind();
        looseBind.binding(api, configMap.getMap("io.github.lucklike.luckyclient.api.mock.MockApi.baidu"));
        System.out.println(api);
    }
}
