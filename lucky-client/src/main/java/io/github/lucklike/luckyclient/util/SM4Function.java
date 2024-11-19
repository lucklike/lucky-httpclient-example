package io.github.lucklike.luckyclient.util;


import com.luckyframework.httpclient.proxy.spel.function.Function;

public class SM4Function {

    @Function("sm4")
    public static String sm4(String s) throws Exception {
        return SM4Util.decryptEcb(s);
    }

}
