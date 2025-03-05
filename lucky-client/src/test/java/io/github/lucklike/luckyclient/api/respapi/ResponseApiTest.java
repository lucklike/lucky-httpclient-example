package io.github.lucklike.luckyclient.api.respapi;

import com.luckyframework.common.ConfigurationMap;
import com.luckyframework.httpclient.core.meta.ClientCookie;
import com.luckyframework.httpclient.core.meta.ContentType;
import com.luckyframework.httpclient.core.meta.Header;
import com.luckyframework.httpclient.core.meta.HttpHeaderManager;
import com.luckyframework.httpclient.core.meta.Request;
import com.luckyframework.httpclient.core.meta.Response;
import com.luckyframework.io.MultipartFile;
import com.luckyframework.serializable.SerializationTypeToken;
import io.github.lucklike.luckyclient.api.mock.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ResponseApiTest {

    @Resource
    private ResponseApi api;

    @Test
    void response() {
        Response response = api.response();


        /*-----------------------获取请求实例------------------------------*/
        Request request = response.getRequest();

        /*-----------------------获取状态码------------------------------*/
        int status = response.getStatus();

        /*-----------------------获取Content-Type信息------------------------------*/
        ContentType contentType = response.getContentType();
        String mimeType = contentType.getMimeType();
        Charset charset = contentType.getCharset();

        /*-----------------------获取响应头信息------------------------------*/
        HttpHeaderManager headerManager = response.getHeaderManager();
        Header header = headerManager.getFirstHeader("X-RESPONSE-DATA");
        // 获取整体值
        Object headerValue = header.getValue();
        // 获取内部某个变量值
        String inameValue = header.getInternalValue("iname");

        /*-----------------------获取Cookie信息------------------------------*/
        List<ClientCookie> cookieList = response.getResponseCookies();
        for (ClientCookie clientCookie : cookieList) {
                // 获取Cookie 的键值对
                String name = clientCookie.getName();
                String value = clientCookie.getValue();

                // 获取其他信息
                String comment = clientCookie.getComment();
                // 适用的域名
                String domain = clientCookie.getDomain();
                // 适用的 URL 路径
                String path = clientCookie.getPath();
                // 版本号
                int version = clientCookie.getVersion();
                // 创建时间
                Date createTime = clientCookie.getCreateTime();
                // 绝对过期时间（GMT）
                Date expireTime = clientCookie.getExpireTime();
                // 相对过期时间（秒）
                int maxAge = clientCookie.getMaxAge();
                // 是否已经过期
                boolean expired = clientCookie.isExpired();
                // 是否仅HTTPS传输
                boolean secure = clientCookie.isSecure();
                // 控制跨站请求行为（CSRF 防护）
                String sameSite = clientCookie.getSameSite();
                // 是否只读
                boolean httpOnly = clientCookie.isHttpOnly();
        }

        /*-----------------------获取响应体信息------------------------------*/
        // 获取String格式的响应体
        String stringResult = response.getStringResult();
        // 获取UTF8格式的String响应体
        String stringResultUtf8 = response.getStringResult(StandardCharsets.UTF_8);
        // 获取byte[]格式的响应体
        byte[] bytes = response.getResult();
        // 获取InputStream格式的响应体
        InputStream inputStream = response.getInputStream();
        // 获取MultipartFile格式的响应体
        MultipartFile multipartFile = response.getMultipartFile();
        // 将响应体转为User类型实体类对象
        User user = response.getEntity(User.class);
        // 使用SerializationTypeToken将响应体转化为带复杂泛型类型的实体类对象
        Map<String, List<Object>> mapList = response.getEntity(new SerializationTypeToken<Map<String, List<Object>>>() {});
    }
}