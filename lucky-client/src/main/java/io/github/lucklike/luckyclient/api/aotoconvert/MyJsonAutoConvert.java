package io.github.lucklike.luckyclient.api.aotoconvert;

import com.luckyframework.httpclient.core.convert.JsonAutoConvert;
import com.luckyframework.httpclient.core.meta.Response;
import io.github.lucklike.httpclient.config.LocatorAutoConvert;
import io.github.lucklike.httpclient.config.RType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Type;

/**
 * @author fukang
 * @version 1.0.0
 * @date 2025/3/3 22:47
 */
@Component
public class MyJsonAutoConvert extends LocatorAutoConvert {

    @Resource
    private JsonAutoConvert jsonAutoConvert;

    @Override
    public boolean can(Response resp, Type type) {
        return jsonAutoConvert.can(resp, type);
    }

    @Override
    public <T> T convert(Response resp, Type type) {
        return jsonAutoConvert.convert(resp, type);
    }

    @Override
    public Integer index() {
        return super.index();
    }

    @Override
    public RType rType() {
        return RType.COVER;
    }

    @Override
    public Class<? extends Response.AutoConvert> indexClass() {
        return JsonAutoConvert.class;
    }
}
