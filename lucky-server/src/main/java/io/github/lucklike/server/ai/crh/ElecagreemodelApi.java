package io.github.lucklike.server.ai.crh;

import com.luckyframework.httpclient.proxy.annotations.HeaderParam;
import com.luckyframework.httpclient.proxy.annotations.MultiFile;
import com.luckyframework.httpclient.proxy.annotations.Post;
import com.luckyframework.httpclient.proxy.annotations.PrintLog;
import com.luckyframework.httpclient.proxy.annotations.QueryParam;
import io.github.lucklike.httpclient.discovery.HttpClient;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@PrintLog
@HttpClient("http://localhost:8015/elecagreemodel/")
public interface ElecagreemodelApi {

    @Post("fundSumaryPublish")
    Map<String, Object> fundSumaryPublish(
            @HeaderParam String authorization,
            @QueryParam String agreement_type,
            @MultiFile MultipartFile[] files
    );

}
