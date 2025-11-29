package io.github.lucklike.luckyclient.api.aotoconvert;

import com.google.gson.Gson;
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
        return ContentType.APPLICATION_JSON.isCompatibleWith(resp.getContentType());
    }

    @Override
    public <T> T convert(Response resp, Type type) {
        return gson.fromJson(resp.getStringResult(), type);
    }
}
