package io.github.lucklike.luckyclient.doc;

import com.luckyframework.httpclient.core.meta.RequestMethod;
import com.luckyframework.httpclient.proxy.annotations.Async;
import com.luckyframework.httpclient.proxy.annotations.BasicAuth;
import com.luckyframework.httpclient.proxy.annotations.BinaryBody;
import com.luckyframework.httpclient.proxy.annotations.CookieParam;
import com.luckyframework.httpclient.proxy.annotations.Get;
import com.luckyframework.httpclient.proxy.annotations.HeaderParam;
import com.luckyframework.httpclient.proxy.annotations.HttpProxy;import com.luckyframework.httpclient.proxy.annotations.HttpRequest;
import com.luckyframework.httpclient.proxy.annotations.JavaBody;
import com.luckyframework.httpclient.proxy.annotations.JsonBody;
import com.luckyframework.httpclient.proxy.annotations.JsonParam;
import com.luckyframework.httpclient.proxy.annotations.MethodParam;
import com.luckyframework.httpclient.proxy.annotations.MultiData;
import com.luckyframework.httpclient.proxy.annotations.MultiFile;
import com.luckyframework.httpclient.proxy.annotations.Post;
import com.luckyframework.httpclient.proxy.annotations.PrintLog;
import com.luckyframework.httpclient.proxy.annotations.PropertiesJson;
import com.luckyframework.httpclient.proxy.annotations.PropertiesJsonArray;
import com.luckyframework.httpclient.proxy.annotations.ProtobufBody;
import com.luckyframework.httpclient.proxy.annotations.QueryParam;
import com.luckyframework.httpclient.proxy.annotations.RefParam;
import com.luckyframework.httpclient.proxy.annotations.StaticHeader;
import com.luckyframework.httpclient.proxy.annotations.StaticJsonBody;
import com.luckyframework.httpclient.proxy.annotations.StaticRef;
import com.luckyframework.httpclient.proxy.annotations.StaticUserInfo;
import com.luckyframework.httpclient.proxy.annotations.StaticXmlBody;
import com.luckyframework.httpclient.proxy.annotations.URLEncoder;
import com.luckyframework.httpclient.proxy.annotations.URLEncoderQuery;
import com.luckyframework.httpclient.proxy.annotations.Url;
import com.luckyframework.httpclient.proxy.annotations.UserInfo;
import com.luckyframework.httpclient.proxy.annotations.XmlBody;
import io.github.lucklike.entity.request.proto.PersonOuterClass;
import io.github.lucklike.httpclient.annotation.HttpClient;import io.github.lucklike.httpclient.annotation.HttpClientComponent;
import io.github.lucklike.luckyclient.api.mock.User;

import java.io.File;
import java.io.InputStream;
import java.io.Reader;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.Map;

@PrintLog
@HttpClient("http://localhost:8080/")
public interface LuckyApi {

    @StaticHeader("Accept: text/plain")
    @Get("http://localhost:8080/lucky/hello")
    String hello(@Url String method);

    @Post("http://localhost:8080/lucky/hello")
    @MultiData
    String text(String name, Integer age, String email);


    @Post("http://localhost:8080/multipartFormData")
    String file(@MultiData Map<String, Object> txtForm, @MultiFile File file);


    @Post("http://localhost:8080/multipartFormData")
    String fileArray(@MultiData Map<String, Object> txtForm, @MultiFile File[] files);

    @Post("http://localhost:8080/multipartFormData")
    String fileList(@MultiData Map<String, Object> txtForm, @MultiFile List<File> fileList);

    @Post("http://localhost:8080/multipartFormData")
    String strResource(@MultiFile String path);


    @Post("http://localhost:8080/multipartFormData")
    String byteFile(@MultiFile(fileName = "test.txt") byte[] bytes);

    @Post("http://localhost:8080/multipartFormData")
    String byteFiles(@MultiFile(fileName = "test-{_index_}.jpg") byte[][] bytes);

    @Post("http://localhost:8080/multipartFormData")
    String byteMap(@MultiFile Map<String, Byte[]> byteMap);


    @Post("http://localhost:8080/multipartFormData")
    String byteFile(@MultiFile(fileName= "#{fileName}") byte[] bytes, String fileName);

    @Post("http://localhost:8080/json")
    Map<String, Object> jsonTest(@JsonBody Map<String, Object> jsonMap);

    @Post("http://localhost:8080/json")
    Map<String, Object> jsonTest2(@JsonBody User user);

    @Post("http://localhost:8080/json")
    Map<String, Object> javaTest2(@JavaBody User user);


    @Post("http://localhost:8080/json")
    Map<String, Object> jsonParam(@JsonParam("userName") String name, @JsonParam Integer age);

    @StaticJsonBody("``#{#read(#resource('classpath:books.json'))}``")
    @Post("http://localhost:8080/json")
    Map<String, Object> staticJsonBody(String bookA, String bookB);

    @PropertiesJson({
            "id=1234",
            "name=Tom",
            "email=tom@gmail.com",
            "phone=17366542234",
            "userAddress[0]=地址1",
            "userAddress[1]=地址2"
    })
    @Post("http://localhost:8080/json")
    Map<String, Object> staticJsonBody();



    @PropertiesJson({
            "email=#{email}",           // 取参数列表值
            "phone=${user.tome.phone:124}", // 取环境变量中的配置
            "userAddress[0]=地址1",
            "userAddress[1]=地址2"
    })
    @Post("http://localhost:8080/json")
    Map<String, Object> propertiesJson3(String email, @JsonParam String id, @JsonParam("userName") String name);


    @PropertiesJsonArray({
            "$[0].id=111",
            "$[0].name=Jack",
            "$[1].id=222",
            "$[1].name=Tom",
    })
    @Post("http://localhost:8080/json")
    Map<String, Object> propertiesJsonArray();


    @StaticXmlBody(
            "    <user>\n" +
            "        <email>tom@gmail.com</email>\n" +
            "        <id>1234</id>\n" +
            "        <name>Tom</name>\n" +
            "        <phone>17366542234</phone>\n" +
            "        <userAddress>地址1</userAddress>\n" +
            "        <userAddress>地址2</userAddress>\n" +
            "    </user>")
    @Post("http://localhost:8080/xml")
    String xmlTest(@XmlBody User xmlMap);

    @Post("http://localhost:8864/protobuf/person")
    PersonOuterClass.Person protobuf(@ProtobufBody PersonOuterClass.Person person);

    @Post("http://localhost:8864/protobuf/person")
    PersonOuterClass.Person protobufB(@BinaryBody(mimeType = "application/x-protobuf") byte[] bytes);

    @Get("http://localhost:8080/userInfo")
    String userInfo(@UserInfo String userInfo);

    @Get("http://localhost:8080/userInfo")
    void br(@BinaryBody File path);


    @StaticUserInfo("#{username}:#{password}")
    @Get("http://localhost:8080/userInfo")
    String userInfo(String username, String password);


    @Get("http://localhost:8080/ref")
    String ref(@RefParam String ref);

    @StaticRef("section1=#{section}")
    @Get("http://localhost:8080/ref")
    String ref2(String section);

    @Post("http://localhost:8080/binary")
    void binary(@BinaryBody byte[] bytes, @QueryParam String filename);

    @Post("http://localhost:8080/binary")
    void binary(@BinaryBody Byte[] bytes, @HeaderParam("X-FILE-NAME") String filename);

    @Post("http://localhost:8080/binary")
    void binary(@BinaryBody String resourcePath);

    @StaticHeader("X-FILE-NAME: #{file.getName()}")
    @Post("http://localhost:8080/binary")
    void binary(@BinaryBody File file);

    @Post("http://localhost:8080/binary")
    void binary(@BinaryBody InputStream in);

    @Post("http://localhost:8080/binary")
    void binary(@BinaryBody ByteBuffer byteBuffer);

    @Post("http://localhost:8080/binary")
    void binary(@BinaryBody Reader reader);

//    @Async
    @Post("http://localhost:8080/async/void")
    void async(@MultiFile File file);

    @HttpProxy(host = "192.168.0.111", port = "8080", username = "You User Name", password = "You Password")
    @Get("/proxy/http")
    String httpProxy();
}
