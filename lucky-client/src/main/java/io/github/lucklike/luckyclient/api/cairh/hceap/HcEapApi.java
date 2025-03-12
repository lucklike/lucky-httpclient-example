package io.github.lucklike.luckyclient.api.cairh.hceap;

import com.luckyframework.httpclient.core.meta.DefaultRequest;
import com.luckyframework.httpclient.core.meta.Request;
import com.luckyframework.httpclient.proxy.annotations.HttpRequest;
import com.luckyframework.httpclient.proxy.annotations.JsonBody;
import com.luckyframework.httpclient.proxy.annotations.MultiData;
import com.luckyframework.httpclient.proxy.annotations.Post;
import com.luckyframework.httpclient.proxy.context.MethodContext;
import com.luckyframework.httpclient.proxy.spel.hook.Lifecycle;
import com.luckyframework.httpclient.proxy.spel.hook.callback.Callback;
import io.github.lucklike.httpclient.annotation.HttpClient;
import org.springframework.beans.factory.annotation.Value;

import java.util.LinkedHashMap;
import java.util.Map;

@HttpClient("${cairh.hceap.url}")
public interface HcEapApi {

    @Post("/arch/v1/getSignature.json")
    String getSignature(@JsonBody Map<String, Object> paramMap);

    @MultiData
    @Post("/hseap/v1/fileUpload.json")
    String uploadFile(String fileName);


    @Callback(lifecycle = Lifecycle.REQUEST_INIT)
    static void addCommonParam(MethodContext mc,
                               Request request,
                               HcEapApi hcEapApi,
                               @Value("${cairh.hceap.appKey}") String appKey,
                               @Value("${cairh.hceap.appSecret}")String appSecret) {
        String methodName = mc.getCurrentAnnotatedElement().getName();

        if (methodName.equals("getSignature")) {
            Map<String, Object> paramMap = (Map<String, Object>) mc.getArguments()[0];
            paramMap.put("appKey", appKey);
            paramMap.put("appSecret", appSecret);
        } else {
            Map<String, Object> signatureMap = new LinkedHashMap<>();
            HttpRequest reqAnn = mc.getMergedAnnotation(HttpRequest.class);
            signatureMap.put("url", reqAnn.url());
            signatureMap.put("method", reqAnn.method());
            ((DefaultRequest) request).setUrlTemplate(hcEapApi.getSignature(signatureMap));
        }
    }
}
