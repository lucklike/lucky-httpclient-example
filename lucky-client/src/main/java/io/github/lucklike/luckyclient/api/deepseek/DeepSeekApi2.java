package io.github.lucklike.luckyclient.api.deepseek;

import com.luckyframework.httpclient.proxy.annotations.ObjectGenerate;
import com.luckyframework.httpclient.proxy.annotations.Post;
import com.luckyframework.httpclient.proxy.annotations.StaticHeader;
import com.luckyframework.httpclient.proxy.annotations.StaticJsonBody;
import com.luckyframework.httpclient.proxy.creator.Scope;
import com.luckyframework.httpclient.proxy.sse.Sse;
import io.github.lucklike.httpclient.annotation.HttpClient;

/**
 * @author fukang
 * @version 1.0.0
 * @date 2025/2/28 01:55
 */
@HttpClient("${DeepSeek.url}")
public interface DeepSeekApi2 {


    @Sse(listenerClass = DeepSeekAnnotationStandardEventListener.class)
    @StaticJsonBody("``#{#read(#resource('classpath:deepseek-param.json'))}``")
    @StaticHeader("Authorization: Bearer ${DeepSeek.apiKey}")
    @Post("/chat/completions")
    void completions(String content);


    /*
       通过@Sse注解的listener来绑定事件监听器，scope = Scope.PROTOTYPE 表示DeepSeekEventListener的创建模式为多例，即每次请求都会创建一个全新的实例
     */
    @Sse(listener = @ObjectGenerate(clazz = DeepSeekEventListener.class, scope = Scope.PROTOTYPE))
    @StaticJsonBody("``#{#read(#resource('classpath:deepseek-param.json'))}``")
    @StaticHeader("Authorization: Bearer ${DeepSeek.apiKey}")
    @Post("/chat/completions")
    void completions2(String content);


    /*
       通过参数来传递事件监听器
     */
    @Sse
    @StaticJsonBody("``#{#read(#resource('classpath:deepseek-param.json'))}``")
    @StaticHeader("Authorization: Bearer ${DeepSeek.apiKey}")
    @Post("/chat/completions")
    void completions3(String content, DeepSeekEventListener listener);


    @DeepSeekChat
    @Post("/chat/completions")
    void completions4(String content);
}
