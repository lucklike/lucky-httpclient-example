package io.github.lucklike.luckyclient.api.simple;

import com.luckyframework.httpclient.proxy.annotations.AutoRedirect;
import com.luckyframework.httpclient.proxy.annotations.Get;
import com.luckyframework.httpclient.proxy.annotations.StaticQuery;
import com.luckyframework.httpclient.proxy.spel.RootVar;
import com.luckyframework.httpclient.proxy.spel.RootVarLit;
import io.github.lucklike.httpclient.annotation.HttpClient;

import java.util.HashMap;
import java.util.Map;

@AutoRedirect
@HttpClient
public interface AnnBilibiliApi {

    @RootVar
    Map<String, Object> $var = new HashMap<String, Object>() {{
        put("userDir", "${user.dir}");
        put("javaVersion", "${java.version}");
        put("osName", "${os.name}");
        put("osArch", "${os.arch}");
        put("nanoId", "#{#nanoid(12)}");
    }};

    @StaticQuery({
            "dir=#{$var.userDir}",
            "javaVersion=#{$var.javaVersion}",
            "os=#{$var.osName}",
            "osArch=#{$var.osArch}",
            "r=#{$var.nanoId}"
    })
    @Get("http://www.bilibili.com")
    String index();
}
