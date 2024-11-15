package io.github.lucklike.luckyclient.api.simple;

import com.luckyframework.httpclient.proxy.annotations.AutoRedirect;
import com.luckyframework.httpclient.proxy.annotations.Get;
import com.luckyframework.httpclient.proxy.annotations.StaticQuery;
import com.luckyframework.httpclient.proxy.spel.SpELImport;
import com.luckyframework.httpclient.proxy.spel.var.RootVar;
import io.github.lucklike.httpclient.annotation.HttpClient;

import java.util.HashMap;
import java.util.Map;

@AutoRedirect
@HttpClient
@SpELImport(BilibiliFunction.class)
public interface AnnBilibiliApi {

    @RootVar(unfold = true)
    Map<String, Object> _var = new HashMap<String, Object>() {{
        put("userDir", "${user.dir}");
        put("javaVersion", "${java.version}");
        put("osName", "${os.name}");
        put("osArch", "${os.arch}");
        put("nanoId", "#{#nanoid(12)}");
    }};

    @StaticQuery({
            "dir=#{userDir}",
            "javaVersion=#{javaVersion}",
            "os=#{osName}",
            "osArch=#{osArch}",
            "r=#{nanoId}"
    })
    @Get("http://www.bilibili.com")
    String index();
}
