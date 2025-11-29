package io.github.lucklike.server.ai.crh;

import com.luckyframework.httpclient.core.meta.HttpFile;
import com.luckyframework.httpclient.proxy.annotations.Header;
import com.luckyframework.httpclient.proxy.annotations.MultiFile;
import com.luckyframework.httpclient.proxy.annotations.MultipartFormData;
import com.luckyframework.httpclient.proxy.annotations.Post;
import com.luckyframework.httpclient.proxy.annotations.PrintLog;
import com.luckyframework.httpclient.proxy.annotations.Query;
import com.luckyframework.httpclient.proxy.annotations.StaticHeader;
import io.github.lucklike.httpclient.discovery.HttpClient;
import io.github.lucklike.server.ai.crh.req.FundSumaryPublishRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@PrintLog
@HttpClient("http://localhost:8015/elecagreemodel/")
public interface ElecagreemodelApi {

    @Post("fundSumaryPublish")
    Map<String, Object> fundSumaryPublish(
            @Header String authorization,
            @Query String agreement_type,
            @MultiFile MultipartFile[] files
    );

    @MultipartFormData(
            txt = {"agreement_type=#{request.agreement_type}"},
            file = {"files=#{#toHttpFileArray(request.files)}"}
    )
    @StaticHeader("Authorization: #{request.authorization}")
    @Post("fundSumaryPublish")
    String fundSumaryPublish(FundSumaryPublishRequest request);


    static HttpFile[] toHttpFileArray(MultipartFile[] files) throws IOException {
        HttpFile[] httpFiles = new HttpFile[files.length];
        for (int i = 0; i < files.length; i++) {
            httpFiles[i] = new HttpFile(files[i].getInputStream(), files[i].getOriginalFilename());
        }
        return httpFiles;
    }
}
