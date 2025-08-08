package io.github.lucklike.luckyclient.api.spelImport;

import com.luckyframework.common.ConfigurationMap;
import com.luckyframework.httpclient.core.meta.Request;
import com.luckyframework.httpclient.core.meta.Response;
import com.luckyframework.httpclient.proxy.annotations.Get;
import com.luckyframework.httpclient.proxy.annotations.StaticQuery;
import com.luckyframework.httpclient.proxy.context.MethodContext;
import com.luckyframework.httpclient.proxy.mock.Mock;
import com.luckyframework.httpclient.proxy.mock.MockResponse;
import com.luckyframework.httpclient.proxy.spel.SpELImport;
import com.luckyframework.httpclient.proxy.spel.hook.callback.Callback;
import com.luckyframework.httpclient.proxy.spel.hook.callback.Var;
import io.github.lucklike.httpclient.discovery.HttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import static com.luckyframework.httpclient.proxy.spel.hook.Lifecycle.CLASS;
import static com.luckyframework.httpclient.proxy.spel.hook.Lifecycle.REQUEST;

@Mock
@SpELImport(MyTools.class)
@HttpClient("http://127.0.0.1:8080/import/")
public interface SpELImportApi {

    @StaticQuery({
            "join=#{#join(param1, param2)}",
            "copy=#{#copy(3, $args$)}"
    })
    @Get("teat1")
    String importTest(String param1, String param2);

}

/**
 * 工具类
 */
class MyTools {

    /**
     * 声明变量，变量来源为classpath下的books.json文件
     * 导入时机为ClassContent初始化后
     */
    @Var(lifecycle = CLASS)
    private static final String $conf = "#{fmap('classpath:books.json')}";

    /**
     * 声明回调函数，用来添加公共参数到请求中
     * 执行时机为Request对象初始化完成后
     *
     * @param request 请求对象
     * @param env     环境变量
     */
    @Callback(lifecycle = REQUEST)
    public static void addCommonParam(Request request, @Autowired Environment env) {
        request.addHeader("Os-Name", env.getProperty("os.name"));
        request.addHeader("Java-Version", env.getProperty("java.version"));
    }

    /**
     * 工具函数1，被导入后可以直接在SpEL表达式中使用
     * 例如：#join('abbc', 'xyz');
     *
     * @param args 要拼接的字符串
     * @return 拼接后的字符串
     */
    public static String join(String... args) {
        return String.join(",", args);
    }

    /**
     * 工具函数1，被导入后可以直接在SpEL表达式中使用
     * 例如：#copy(3, 'abbc', 'xyz');
     *
     * @param num  拷贝次数
     * @param args 要拷贝拼接的字符串
     * @return 拷贝拼接后的字符串
     */
    public static String copy(int num, String... args) {
        StringBuilder builder = new StringBuilder();
        for (String arg : args) {
            for (int i = 0; i < num; i++) {
                builder.append(arg);
            }
            builder.append("-");
        }
        return builder.toString();
    }

    /**
     * Mock方法，用于生成Mock响应发对象
     *
     * @param mc 方法上下文对象
     * @return Mock响应对象
     */
    public static Response importTest$Mock(MethodContext mc) {
        ConfigurationMap $confMap = mc.getRootVar("$conf", ConfigurationMap.class);
        return MockResponse.create().status(200).header("Tag", "IMPORT-TEST-001").json($confMap);
    }
}
