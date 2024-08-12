package io.github.lucklike.luckyclient.api.baiduai;

import com.luckyframework.common.ConfigurationMap;
import com.luckyframework.common.Console;
import com.luckyframework.httpclient.core.meta.HeaderMataData;
import com.luckyframework.httpclient.proxy.sse.Event;
import com.luckyframework.httpclient.proxy.sse.EventListener;
import com.luckyframework.httpclient.proxy.sse.Message;
import io.github.lucklike.luckyclient.api.util.DelayedOutput;

public class BaiduAIEventListener implements EventListener {


    @Override
    public void onOpen(Event<HeaderMataData> event) throws Exception {
        Console.printlnMulberry("AI:");
    }

    @Override
    public void onMessage(Event<Message> event) throws Exception {
        Message message = event.getMessage();
        ConfigurationMap confMap = message.jsonDataToMap();
        if (!confMap.getBoolean("is_end")) {
            String result = confMap.getString("result");
            DelayedOutput.output(result);
        } else {
            Console.printlnMulberry("\n");
            DelayedOutput.clearOutputLength();
        }
    }
}
