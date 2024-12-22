package io.github.lucklike.server.ai.util;


import com.luckyframework.httpclient.proxy.spel.FunctionAlias;

public class SM4Function {

    @FunctionAlias("sm4")
    public static String sm4(String s) throws Exception {
        return SM4Util.decryptEcb(s);
    }

    @FunctionAlias("_sm4_")
    public static String _sm4_(String s) throws Exception {
        return SM4Util.encryptEcb(s);
    }

}
