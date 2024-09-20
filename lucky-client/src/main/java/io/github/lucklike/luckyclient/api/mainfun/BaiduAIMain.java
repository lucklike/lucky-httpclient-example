package io.github.lucklike.luckyclient.api.mainfun;

import com.luckyframework.common.ConfigurationMap;
import com.luckyframework.common.Resources;
import com.luckyframework.httpclient.proxy.HttpClientProxyObjectFactory;
import io.github.lucklike.luckyclient.util.SM4Function;

public class BaiduAIMain {

   public static final ConfigurationMap CONFIG_MAP;

   static {
       String configFilePath = System.getProperty("baidu.ai.config.file");
       CONFIG_MAP = Resources.yamlResourceAsConfigMap(configFilePath);
       CONFIG_MAP.addProperty("BAIDU.API.CONFIG.FILE", configFilePath);
   }

    public static void main(String[] args) {
        getAI().questionsAndAnswersTest();
    }



    private static BaiduAI getAI() {
        HttpClientProxyObjectFactory factory = new HttpClientProxyObjectFactory();
        factory.addSpringElFunctionClass(SM4Function.class);
        factory.addSpringElRootVariables(CONFIG_MAP);
        return factory.getProxyObject(BaiduAI.class);
    }
}
