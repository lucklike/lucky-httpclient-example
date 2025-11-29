package io.github.lucklike.luckyclient.api.retry;

import com.luckyframework.httpclient.generalapi.describe.Describe;
import com.luckyframework.httpclient.proxy.annotations.Get;
import com.luckyframework.httpclient.proxy.context.MethodContext;
import com.luckyframework.httpclient.proxy.mock.Mock;
import com.luckyframework.httpclient.proxy.spel.hook.Lifecycle;
import com.luckyframework.httpclient.proxy.spel.hook.callback.Callback;
import io.github.lucklike.httpclient.discovery.HttpClient;

/**
 * @author fukang
 * @version 1.0.0
 * @date 2025/9/17 01:46
 */
@Describe(author = "付康")
@HttpClient("http://127.0.0.1:8080")
public interface RetryApi {

    @Get
    @Mock(status = "404", body = "Hello World")
    @Describe("重试测试接口")
    String index();

    @Callback(lifecycle = Lifecycle.REQUEST)
    static void call(MethodContext mc) {
        String str = mc.parseExpression("``@max(4):#{e1()}``");
        System.out.println(str);
    }

    static String e3() {
       return  "#{'hello world'} -- #{1+1}";
    }

    static String e1() {
        return "#{e2()}";
    }

    static String e2() {
        return "#{e3()}";
    }
}
