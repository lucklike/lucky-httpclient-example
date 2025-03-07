package io.github.lucklike.luckyclient.api.async;

import com.luckyframework.httpclient.proxy.annotations.Async;
import com.luckyframework.httpclient.proxy.annotations.Get;
import com.luckyframework.httpclient.proxy.async.Model;
import com.luckyframework.httpclient.proxy.context.MethodContext;
import com.luckyframework.httpclient.proxy.handle.ResultHandler;
import com.luckyframework.httpclient.proxy.mock.Mock;
import com.luckyframework.httpclient.proxy.mock.MockResponse;
import com.luckyframework.httpclient.proxy.spel.hook.AsyncHook;
import com.luckyframework.httpclient.proxy.spel.hook.Lifecycle;
import com.luckyframework.httpclient.proxy.spel.hook.callback.Callback;
import io.github.lucklike.httpclient.annotation.HttpClient;


@Mock
@HttpClient("http://localhost:8864")
public interface AsyncApi {

    @Async
    @Get("get")
    void get(ResultHandler<String> handler);


    static MockResponse asyncApi$Mock() {
        return MockResponse.create().status(200).txt("Hello World");
    }

    @AsyncHook
    @Callback(lifecycle = Lifecycle.RESPONSE)
    static void respCallback(MethodContext mc) {
        System.out.println(Thread.currentThread().getName()+ " --> " +mc.getAsyncTaskExecutor());
    }
}
