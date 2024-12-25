package io.github.lucklike.luckyclient.api.server.ann;

import com.luckyframework.httpclient.generalapi.describe.Describe;
import com.luckyframework.httpclient.proxy.annotations.Get;
import com.luckyframework.httpclient.proxy.annotations.HttpExec;
import com.luckyframework.httpclient.proxy.annotations.JsonBody;
import com.luckyframework.httpclient.proxy.annotations.JsonParam;
import com.luckyframework.httpclient.proxy.annotations.MultiData;
import com.luckyframework.httpclient.proxy.annotations.MultiFile;
import com.luckyframework.httpclient.proxy.annotations.Post;
import com.luckyframework.httpclient.proxy.annotations.PropertiesJson;
import com.luckyframework.httpclient.proxy.annotations.PropertiesJsonArray;
import com.luckyframework.httpclient.proxy.annotations.StaticJsonBody;
import io.github.lucklike.entity.request.User;
import io.github.lucklike.entity.response.Result;
import io.github.lucklike.httpclient.annotation.HttpClientComponent;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author fukang
 * @version 1.0.0
 * @date 2024/7/9 01:45
 */

@HttpClientComponent
@Describe(author = "付康", version = "1.0.0", contactWay = "17363312985")
public interface AnnUserApi extends LuckyServerApi {

    @Describe("注册用户")
    @Post("/user/post")
    User postUser(@JsonBody User user);

    @Describe("获取用户信息")
    @Get("/user/get")
    Result<User> getUser();

    @Describe("异常测试")
    @Get("/user/error?d=123")
    void error();

    @PropertiesJson({"a.b=123", "a.c=567"})
    @Post("/user/post")
    void postTest(@JsonParam String name, @JsonParam Integer age);


    @PropertiesJsonArray({
            "$[0].a.b=12",
            "$[0].a.c=llll",
            "$[1].a.b=2212",
            "$[1].a.c=gggg"})
    @Post("/user/post")
    void postTest2(Integer b, String c);


    @StaticJsonBody("``#{#read('classpath:books.json')}``")
    @Post("/user/post")
    void postTest3(Integer a, String c);

    @HttpExec.okhttp
    @Post("/file/upload")
    String upload(@MultiData String id, @MultiFile String file);
}
