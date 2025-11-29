package io.github.lucklike.luckyclient.api.mock;

import com.luckyframework.httpclient.proxy.annotations.JsonBody;
import com.luckyframework.httpclient.proxy.annotations.JsonParam;
import com.luckyframework.httpclient.proxy.annotations.Post;
import com.luckyframework.httpclient.proxy.annotations.ResourceJson;
import com.luckyframework.httpclient.proxy.mock.Mock;
import io.github.lucklike.httpclient.annotation.EnvironmentJson;
import io.github.lucklike.httpclient.discovery.HttpClient;
import io.github.lucklike.luckyclient.api.mock.type.User;

/**
 * @author fukang
 * @version 1.0.0
 * @date 2025/11/21 01:29
 */
@Mock
@HttpClient("http://localhost:8080")
public interface JsonMockApi {

    @Post("envJson")
    @EnvironmentJson("env-json.object")
    String envJson(@JsonParam User user);

    @Post("envJsonArray")
    @EnvironmentJson(value = "env-json.array",type = EnvironmentJson.ARRAY)
    String envJsonArray(@JsonParam("[2]") User user);

    @Post("resJson")
    @ResourceJson("classpath:/param-temp/user.json")
    String resJson(@JsonParam(unfold = true) User user);

    @Post("resJsonArray")
    @ResourceJson("classpath:/param-temp/users.xml")
    String resJsonArray(@JsonParam("[3]") User user);

    @Post("resJsonArrayProp")
    @ResourceJson("classpath:/param-temp/users.yaml")
    String resJsonArrayProp(@JsonParam("[3]") User user);
}
