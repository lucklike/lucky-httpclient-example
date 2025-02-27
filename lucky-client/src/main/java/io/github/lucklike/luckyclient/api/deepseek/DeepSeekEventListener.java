package io.github.lucklike.luckyclient.api.deepseek;

import com.luckyframework.httpclient.core.meta.Response;
import com.luckyframework.httpclient.proxy.sse.Event;
import com.luckyframework.httpclient.proxy.sse.EventListener;

/**
 * @author fukang
 * @version 1.0.0
 * @date 2025/2/28 01:39
 */
public class DeepSeekEventListener implements EventListener {

    @Override
    public void onOpen(Event<Response> event) throws Throwable {
        System.out.println("连接已建立！");
    }

    @Override
    public void onText(Event<String> event) throws Exception {
        System.out.println("接收到服务器消息：" + event.getMessage());
    }

    @Override
    public void onError(Event<Throwable> event) {
        System.out.println("发生异常: " + event.getMessage().getMessage());
    }

    @Override
    public void onClose(Event<Void> event) {
        System.out.println("连接已关闭");
    }
}
