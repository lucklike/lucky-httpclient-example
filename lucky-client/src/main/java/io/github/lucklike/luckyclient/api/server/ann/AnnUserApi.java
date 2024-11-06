package io.github.lucklike.luckyclient.api.server.ann;

import com.luckyframework.common.DateUtils;
import com.luckyframework.httpclient.proxy.annotations.ExceptionHandle;
import com.luckyframework.httpclient.proxy.annotations.Get;
import com.luckyframework.httpclient.proxy.annotations.JsonBody;
import com.luckyframework.httpclient.proxy.annotations.Post;
import com.luckyframework.httpclient.proxy.annotations.PrintLogProhibition;
import com.luckyframework.httpclient.proxy.fuse.LengthWindowFuseStrategy;
import com.luckyframework.httpclient.proxy.fuse.TimeWindowFuseStrategy;
import com.luckyframework.httpclient.proxy.handle.ExceptionFallback;
import io.github.lucklike.entity.request.User;
import io.github.lucklike.entity.response.Result;
import io.github.lucklike.httpclient.annotation.HttpClientComponent;
import io.github.lucklike.luckyclient.api.server.fallbak.AnnUserApiFallBack;
import io.github.lucklike.luckyclient.api.server.fallbak.MyIdGenerator;

/**
 * @author fukang
 * @version 1.0.0
 * @date 2024/7/9 01:45
 */
@PrintLogProhibition
//@TimeWindowFuseStrategy(timeInterval = 10,  maxFailCount = 3, maxTimeoutRatio = 0.6, slideUnit = 2, idGenerator = MyIdGenerator.class)
@LengthWindowFuseStrategy(maxReqSize = 300,  maxFailRatio = 0.5, maxTimeoutRatio = 0.6, idGenerator = MyIdGenerator.class)
@ExceptionFallback(AnnUserApiFallBack.class)
@HttpClientComponent
public interface AnnUserApi extends LuckyServerApi {

    @Post("/user/post")
    User postUser(@JsonBody User user);

    @Get("/user/get")
    @ExceptionHandle
    Result<User> getUser();


    /**
     * 【约定写法】以目标方法名+ExceptionHandle结尾的静态方法可用于处理目标方法的异常
     * {@link #getUser()}方法出现异常时的异常处理方法
     *
     * @param e 异常实例
     */
    static void getUserExceptionHandle(Exception e) throws InterruptedException {
        Thread.sleep(200L);
        System.out.println(DateUtils.time() + " -> " +e);
    }
}
