package io.github.lucklike.luckyclient.api.lucky;

import com.luckyframework.httpclient.core.meta.BodyObject;
import com.luckyframework.httpclient.core.meta.ContentType;
import com.luckyframework.httpclient.core.meta.Request;
import com.luckyframework.httpclient.core.meta.Response;
import com.luckyframework.httpclient.proxy.annotations.ExceptionHandle;
import com.luckyframework.httpclient.proxy.annotations.Post;
import com.luckyframework.httpclient.proxy.annotations.Retryable;
import com.luckyframework.httpclient.proxy.annotations.StaticHeader;
import com.luckyframework.httpclient.proxy.context.MethodContext;
import com.luckyframework.httpclient.proxy.spel.hook.Lifecycle;
import com.luckyframework.httpclient.proxy.spel.hook.callback.Callback;
import com.luckyframework.retry.TaskResult;

/**
 * May you be warm in the three winters, and may you not be cold in the spring.
 *
 * @author fukang
 * @version 1.0.0
 * @date 2024/12/31 00:00
 */
@LAPI
public interface LuckyApi {

    @StaticHeader({
        "date: 2024-12-01",
        "name: #{['768976']}",
        "say_: #{_base64_str('772e55Sf5pel5b+r5LmQ772e')}"
    })
    @Retryable
    @Post("/benediction")
    void benediction();

    /**
     * Callback Function
     *
     * @param request Request
     */
    @Callback(lifecycle = Lifecycle.REQUEST)
    static void callback(Request request) {
        request.setBody(
            BodyObject.builder(
                ContentType.TEXT_PLAIN,
                "Just live your life well."
            )
        );
    }
}
