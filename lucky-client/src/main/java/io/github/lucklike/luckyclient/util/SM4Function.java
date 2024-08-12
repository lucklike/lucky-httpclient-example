package io.github.lucklike.luckyclient.util;

import com.luckyframework.httpclient.proxy.spel.FunctionAlias;

public class SM4Function {

    @FunctionAlias("sm4")
    public static String sm4(String s) throws Exception {
        return SM4Util.decryptEcb(s);
    }

}
