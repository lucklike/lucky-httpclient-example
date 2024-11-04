package io.github.lucklike.luckyclient.api.server.fallbak;

import com.luckyframework.httpclient.core.meta.Request;
import com.luckyframework.httpclient.proxy.context.MethodContext;
import com.luckyframework.httpclient.proxy.fuse.IdGenerator;

import java.net.URL;

public class MyIdGenerator implements IdGenerator {
    @Override
    public Object generateId(MethodContext context, Request request) {
        URL url = request.getURL();
        return String.format("%s:%s%s", url.getHost(), url.getPort(), url.getPath());
    }
}
