package io.github.lucklike.luckyclient.api.server.ann;

import com.luckyframework.common.DateUtils;
import com.luckyframework.httpclient.core.meta.Response;
import com.luckyframework.httpclient.proxy.annotations.ExceptionHandle;
import com.luckyframework.httpclient.proxy.annotations.Get;
import com.luckyframework.httpclient.proxy.annotations.JsonBody;
import com.luckyframework.httpclient.proxy.annotations.ObjectGenerate;
import com.luckyframework.httpclient.proxy.annotations.Post;
import com.luckyframework.httpclient.proxy.annotations.PrintLogProhibition;
import com.luckyframework.httpclient.proxy.annotations.RespConvert;
import com.luckyframework.httpclient.proxy.annotations.StaticHeader;
import com.luckyframework.httpclient.proxy.context.MethodContext;
import com.luckyframework.httpclient.proxy.creator.Scope;
import com.luckyframework.httpclient.proxy.fuse.FixedQuantityFuseStrategy;
import com.luckyframework.httpclient.proxy.fuse.FuseMeta;
import com.luckyframework.httpclient.proxy.handle.ExceptionFallback;
import com.luckyframework.httpclient.proxy.mock.MockResponse;
import io.github.lucklike.entity.request.User;
import io.github.lucklike.entity.response.Result;
import io.github.lucklike.httpclient.annotation.HttpClientComponent;
import io.github.lucklike.luckyclient.api.server.fallbak.AnnUserApiFallBack;
import io.github.lucklike.luckyclient.api.server.fallbak.MyIdGenerator;
import io.github.lucklike.luckyclient.api.server.fuse.MyFuse;
import lombok.extern.slf4j.Slf4j;

/**
 * @author fukang
 * @version 1.0.0
 * @date 2024/7/9 01:45
 */
@PrintLogProhibition
@FixedQuantityFuseStrategy(maxReqSize = 10, maxFailRatio = 0.5, idGenerator = MyIdGenerator.class)
//@ExceptionFallback(AnnUserApiFallBack.class)
@HttpClientComponent
public interface AnnUserApi extends LuckyServerApi {

    @StaticHeader({"H1: 123", "H1: 345"})
    @Post("/user/post")
    User postUser(@JsonBody User user);

    @ExceptionHandle
    @Get("/user/get")
    Result<User> getUser();


    static void getUserExceptionHandle(Exception e) throws InterruptedException {
        Thread.sleep(200L);
        System.out.println(DateUtils.time() + " -> " +e);
    }
}
