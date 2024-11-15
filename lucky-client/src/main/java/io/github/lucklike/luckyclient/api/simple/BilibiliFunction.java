package io.github.lucklike.luckyclient.api.simple;

import com.luckyframework.httpclient.proxy.spel.Namespace;
import com.luckyframework.httpclient.proxy.spel.var.RootVar;
import com.luckyframework.httpclient.proxy.spel.var.Var;

/**
 * @author fukang
 * @version 1.0.0
 * @date 2024/11/14 02:26
 */
@Namespace("bili")
public class BilibiliFunction {

    @RootVar
    private static final String name = "Bilibili-Name";

    @RootVar
    private static final String userDir = "${user.dir}";

    @Var("version")
    private static final String javaVersion = "${java.version}";

    @Var
    private static final String accessKey = "${kuaiTong.accessKey}";

    @Var
    private static final String accessSecret = "${kuaiTong.accessSecret}";
}
