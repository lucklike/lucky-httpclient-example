package io.github.lucklike.luckyclient.api.cairh;

import com.luckyframework.httpclient.proxy.annotations.DownloadToLocal;
import com.luckyframework.httpclient.proxy.annotations.JsonBody;
import com.luckyframework.httpclient.proxy.annotations.Post;
import com.luckyframework.httpclient.proxy.annotations.Retryable;
import io.github.lucklike.httpclient.discovery.HttpClient;

import java.util.Map;

@Retryable
@HttpClient("${cairh.scriptGenerate.url}")
public interface ScriptGenerateApi {


    @Post("generate")
    @DownloadToLocal(saveDir = "D:/test/", filename = "生僻字脚本-#{#nanoid(4)}")
    String getSqlScript(@JsonBody Map<String, Object> body);

}
