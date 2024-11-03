package io.github.lucklike.luckyclient.api.server.ann;

import com.luckyframework.httpclient.core.meta.Response;
import com.luckyframework.httpclient.proxy.annotations.Get;
import com.luckyframework.httpclient.proxy.annotations.JsonBody;
import com.luckyframework.httpclient.proxy.annotations.ObjectGenerate;
import com.luckyframework.httpclient.proxy.annotations.Post;
import com.luckyframework.httpclient.proxy.annotations.RespConvert;
import com.luckyframework.httpclient.proxy.annotations.StaticHeader;
import com.luckyframework.httpclient.proxy.context.MethodContext;
import com.luckyframework.httpclient.proxy.creator.Scope;
import com.luckyframework.httpclient.proxy.fuse.FuseMeta;
import com.luckyframework.httpclient.proxy.handle.ExceptionFallback;
import com.luckyframework.httpclient.proxy.mock.MockResponse;
import io.github.lucklike.entity.request.User;
import io.github.lucklike.entity.response.Result;
import io.github.lucklike.httpclient.annotation.HttpClientComponent;
import io.github.lucklike.luckyclient.api.server.fallbak.AnnUserApiFallBack;
import io.github.lucklike.luckyclient.api.server.fuse.MyFuse;

/**
 * @author fukang
 * @version 1.0.0
 * @date 2024/7/9 01:45
 */
@FuseMeta(fuse = @ObjectGenerate(clazz = MyFuse.class, scope = Scope.CLASS))
@ExceptionFallback(AnnUserApiFallBack.class)
@HttpClientComponent
public interface AnnUserApi extends LuckyServerApi {

//    @Mock
    @StaticHeader({"H1: 123", "H1: 345"})
    @Post("/user/post")
    @RespConvert("#{$body$.lk.opp}")
    User postUser(@JsonBody User user);

    @Get("/user/get")
    Result<User> getUser();

    static Response postUserMock(MethodContext context) {
        return MockResponse.create().json(Result.success(context.getArguments()[0]));
    }
}
