package io.github.lucklike.luckyclient.api.mainfun;

import com.luckyframework.httpclient.proxy.HttpClientProxyObjectFactory;

/**
 * @author fukang
 * @version 1.0.0
 * @date 2024/11/18 02:11
 */
public class DownloadMain {

    public static void main(String[] args) {

        final String urlKey = "url";
        final String saveDir = "saveDir";
        final String fileName = "fileName";
        final String rangeSize = "rangeSize";

        HttpClientProxyObjectFactory factory = new HttpClientProxyObjectFactory();
        factory.addSpringElFunctionClass(SysPropertyFunction.class);


    }

    public static class SysPropertyFunction {


        public static String $(String key) {
            return System.getProperty(key);
        }
    }
}
