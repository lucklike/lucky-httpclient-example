package io.github.lucklike.luckyclient.api;

import com.luckyframework.httpclient.core.meta.BodyObject;
import com.luckyframework.httpclient.core.meta.ContentType;
import com.luckyframework.httpclient.core.meta.Request;
import com.luckyframework.httpclient.proxy.annotations.Post;
import com.luckyframework.httpclient.proxy.annotations.StaticHeader;
import com.luckyframework.httpclient.proxy.spel.hook.Lifecycle;
import com.luckyframework.httpclient.proxy.spel.hook.callback.Callback;
import io.github.lucklike.httpclient.annotation.HttpClient;

/**
 * May you be warm in the three winters, and may you not be cold in the spring.
 *
 * @author fukang
 * @version 1.0.0
 * @date 2024/12/31 00:00
 */
@HttpClient
public interface LuckyApi {

    @StaticHeader({
        "_id_: 768976",
        "date: 2024-12-01",
        "_say: #{#_base64ToStr('772e55Sf5pel5b+r5LmQ772e')"
    })
    @Post("/benediction")
    String benediction();

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
                "Don't feel any debt, just live your life well."
            )
        );
    }
}
