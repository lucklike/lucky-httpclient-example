package io.github.lucklike.luckyclient.api.aotoconvert;

import com.alibaba.nacos.shaded.com.google.gson.Gson;
import com.luckyframework.httpclient.core.meta.ContentType;
import com.luckyframework.httpclient.core.meta.Response;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;

/**
 * @author fukang
 * @version 1.0.0
 * @date 2025/3/3 23:38
 */
@Component
public class GsonAutoConvert implements Response.AutoConvert{

    private final Gson gson = new Gson();

    @Override
    public boolean can(Response resp, Type type) {
        String mimeType = resp.getContentType().getMimeType();
        return ContentType.APPLICATION_JSON.getMimeType().equalsIgnoreCase(mimeType);
    }

    @Override
    public <T> T convert(Response resp, Type type) {
        return gson.fromJson(resp.getStringResult(), type);
    }
}
