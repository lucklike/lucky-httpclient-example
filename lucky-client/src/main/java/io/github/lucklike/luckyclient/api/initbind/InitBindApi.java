package io.github.lucklike.luckyclient.api.initbind;

import com.luckyframework.httpclient.proxy.annotations.JsonBody;
import com.luckyframework.httpclient.proxy.annotations.Post;
import com.luckyframework.httpclient.proxy.annotations.Query;
import com.luckyframework.httpclient.proxy.mock.Mock;
import io.github.lucklike.httpclient.convert.InitBind;
import io.github.lucklike.httpclient.discovery.HttpClient;

import javax.ws.rs.QueryParam;
import java.util.Map;

/**
 * 初始化绑定
 *
 * @author fukang
 * @version 1.0.0
 * @date 2025/9/28 19:33
 */
@InitBind(
    value = {
        "init.bind.#{$class$.getSimpleName}.#{$method$.getName}",
        "init.bind.#{$class$.getSimpleName}.config"
    },
    types = MyRequest.class
)
@HttpClient("http://licalhost:8080")
public interface InitBindApi {

    @Mock(body = "!!!Mock Response!!!")
    @Post("query")
    String query(@JsonBody MyRequest request, @Query Map<String, Object> query);

}
