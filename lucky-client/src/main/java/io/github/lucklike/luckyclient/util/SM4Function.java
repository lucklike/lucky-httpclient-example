package io.github.lucklike.luckyclient.util;


import com.luckyframework.httpclient.proxy.spel.function.Function;

public class SM4Function {

    @Function("sm4")
    public static String sm4(String s) throws Exception {
        return SM4Util.decryptEcb(s);
    }

    @Function("_sm4_")
    public static String _sm4_(String s) throws Exception {
        return SM4Util.encryptEcb(s);
    }

}
