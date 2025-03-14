package io.github.lucklike.server.ai.crh;

import com.luckyframework.httpclient.proxy.annotations.HeaderParam;
import com.luckyframework.httpclient.proxy.annotations.MultiData;
import com.luckyframework.httpclient.proxy.annotations.MultiFile;
import com.luckyframework.httpclient.proxy.annotations.Post;
import com.luckyframework.httpclient.proxy.annotations.PrintLog;
import com.luckyframework.httpclient.proxy.annotations.QueryParam;
import io.github.lucklike.httpclient.discovery.HttpClient;
import org.springframework.core.io.InputStreamSource;

import java.util.Map;

@PrintLog
@HttpClient("http://localhost:8015/elecagreemodel/")
public interface ElecagreemodelApi {

    @Post("fundSumaryPublish?agreement_type=#{agreement_type}")
    Map<String, Object> fundSumaryPublish(@HeaderParam String authorization, String agreement_type, @MultiFile Map<String, InputStreamSource> files);
}
