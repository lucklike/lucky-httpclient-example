package io.github.lucklike.luckyclient.api.deepseek;

import com.luckyframework.httpclient.core.meta.Response;
import com.luckyframework.httpclient.proxy.sse.Event;
import com.luckyframework.httpclient.proxy.sse.OnMessage;
import com.luckyframework.httpclient.proxy.sse.standard.AnnotationStandardEventListener;
import com.luckyframework.reflect.Param;

public class DeepSeekAnnotationStandardEventListener extends AnnotationStandardEventListener {

    @Override
    protected void onOpening(Event<Response> event) throws Exception {
        System.out.println("连接已建立！");
    }

    @Override
    public void onError(Event<Throwable> event) {
        System.out.println("发生异常: " + event.getMessage().getMessage());
    }

    @Override
    protected void onClosed(Event<Void> event) {
        System.out.println("连接已关闭");
    }


    @OnMessage("#{$data$.trim() eq '[DONE]'}")
    public void msgEnd() {
        System.out.println("DeepSeek回复结束.");
    }

    @OnMessage
    public void printMsg(@Param("#{$jdata$.choices[0].delta.content}") String msg) {
        System.out.print(msg);
    }
}
