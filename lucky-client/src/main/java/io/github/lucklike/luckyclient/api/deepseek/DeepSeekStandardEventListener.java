package io.github.lucklike.luckyclient.api.deepseek;

import com.luckyframework.common.ConfigurationMap;
import com.luckyframework.httpclient.core.meta.Response;
import com.luckyframework.httpclient.proxy.sse.Event;
import com.luckyframework.httpclient.proxy.sse.standard.Message;
import com.luckyframework.httpclient.proxy.sse.standard.StandardEventListener;

public class DeepSeekStandardEventListener extends StandardEventListener {

    @Override
    protected void onOpening(Event<Response> event) throws Exception {
        System.out.println("连接已建立！");
    }

    @Override
    protected void onMessage(Event<Message> event) throws Exception {
        Message message = event.getMessage();
        if ("[DONE]".equals(message.getData().trim())) {
            System.out.println("DeepSeek回复结束.");
        } else {
            // 将Json格式的data: 数据转化为ConfigurationMap对象
            ConfigurationMap jsonMap = message.jsonDataToMap();
            // 获取回复内容【choices[0].delta.content】并打印
            System.out.println(jsonMap.getProperty("choices[0].delta.content"));
        }
    }


    @Override
    public void onError(Event<Throwable> event) {
        System.out.println("发生异常: " + event.getMessage().getMessage());
    }


    @Override
    protected void onClosed(Event<Void> event) {
        System.out.println("连接已关闭");
    }
}
