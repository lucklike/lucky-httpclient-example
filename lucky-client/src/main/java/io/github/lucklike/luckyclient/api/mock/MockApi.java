package io.github.lucklike.luckyclient.api.mock;

import com.luckyframework.httpclient.core.meta.HttpHeaders;
import com.luckyframework.httpclient.proxy.annotations.Get;
import com.luckyframework.httpclient.proxy.annotations.Post;
import com.luckyframework.httpclient.proxy.context.MethodContext;
import com.luckyframework.httpclient.proxy.handle.ResultHandler;
import com.luckyframework.httpclient.proxy.mock.Mock;
import com.luckyframework.httpclient.proxy.mock.MockResponse;
import com.luckyframework.httpclient.proxy.mock.SseMock;
import com.luckyframework.httpclient.proxy.sse.EventListener;
import com.luckyframework.httpclient.proxy.sse.Sse;
import com.luckyframework.io.MultipartFile;
import com.luckyframework.reflect.Param;
import io.github.lucklike.httpclient.discovery.HttpClient;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author fukang
 * @version 1.0.0
 * @date 2025/3/2 08:58
 */
@HttpClient("http://localhost:8080")
public interface MockApi {

    @Mock(
            status = "200",
            header = "Content-Type: text/html",
            body = "<!DOCTYPE html><html><body><h1>Hello World</h1></body></html>"
    )
    @Post("html")
    String html();

    @Mock(
        status = "404",
        body = "['#{$req$.getURI().getPath()}'] Not Fount!"
    )
    @Get("notFount")
    String _404();

    @Mock(
        status = "500",
        header = "Content-Type: application/json",
        body = "{\"code\": 500,\"message\": \"java.lang.ArrayIndexOutOfBoundsException: Index 5 out of bounds for length 2\"}"
    )
    @Get("error")
    String _500();

    @Mock(body = "#{#resource('classpath:sheep.png')}")
    @Get("download/png")
    MultipartFile download();

    @Mock(body = "#{#resource('classpath:sheep.png')}")
    @Get("download/png")
    void downloadHandler(ResultHandler<MultipartFile> fileHandler);

    @Mock(mockResp = "#{$this$.mockRespDemo($mc$)}")
    @Post("mockResp")
    String mockResp(String p1, int p2);

    // 构造模拟响应体的方法
    default MockResponse mockRespDemo(MethodContext mc) {
        MockResponse mockResp = MockResponse.create();
        // 设置状态码
        mockResp.status(200);
        // 设置Content-Type
        mockResp.contentType("application/json");
        // 设置Header
        mockResp.header(HttpHeaders.AUTHORIZATION, "234AC2BC3456");
        mockResp.header("CURR-METHOD", mc.getCurrentAnnotatedElement().getName());
        mockResp.header("CURR-METHOD-ARGS", Arrays.toString(mc.getArguments()));

        // 设置响应体
        Map<String, Object> bodyMap = new LinkedHashMap<>();
        bodyMap.put("id", 11);
        bodyMap.put("name", "Lucky Client");
        bodyMap.put("version", "3.0.1");
        bodyMap.put("birthday", new Date());
        mockResp.json(bodyMap);

        return mockResp;
    }

    @Mock(mockFunc = "mockFuncDemo")
    @Post("mockFunc")
    Map<String, Object> mockFunc(String p1, String p2);

    // 构造模拟响应体的方法，SpEL函数方法必须是static修饰的
    static MockResponse mockFuncDemo(Method method, @Param("#{$args$}") Object[] args) {
        MockResponse mockResp = MockResponse.create();
        // 设置状态码
        mockResp.status(200);
        // 设置Content-Type
        mockResp.contentType("application/x-java-serialized-object");
        // 设置Header
        mockResp.header(HttpHeaders.AUTHORIZATION, "234AC2BC3456");
        mockResp.header("CURR-METHOD", method.getName());
        mockResp.header("CURR-METHOD-ARGS", Arrays.toString(args));

        // 设置响应体
        LinkedHashMap<String, Object> bodyMap = new LinkedHashMap<>();
        bodyMap.put("id", 12);
        bodyMap.put("name", "Spring Boot");
        bodyMap.put("version", "2.0.1");
        bodyMap.put("birthday", new Date());
        mockResp.java(bodyMap);

        return mockResp;
    }

    @Mock(enable = "${mock.enable:true}")
    @Get("download/#{fileName}")
    MultipartFile appoint(String fileName);

    // appoint方法约定的Mock方法【${targetMethodName} + $Mock】 -> appoint$Mock
    static MockResponse appoint$Mock(@Param("#{fileName}") String fileName) {
        MockResponse mockResponse = MockResponse.create();
        mockResponse.status(200);
        mockResponse.resource(String.format("classpath:%s", fileName));
        return mockResponse;
    }

    @Sse
    @Mock
    @Post("sse2")
    void sse2(EventListener listener);


    static MockResponse sse2$Mock() {
        MockResponse mockResponse = MockResponse.create();
        mockResponse.status(200);
        mockResponse.sse(SseMock.resource("classpath:sse-mock.data"));
        return mockResponse;
    }


}
