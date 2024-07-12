package io.github.lucklike.luckyclient.api.spark;


import com.luckyframework.common.ConfigurationMap;
import com.luckyframework.httpclient.proxy.sse.Event;
import com.luckyframework.httpclient.proxy.sse.EventListener;
import com.luckyframework.httpclient.proxy.sse.Message;
import com.luckyframework.serializable.SerializationTypeToken;

import java.util.Map;

public class SparkCompletionsEventListener implements EventListener {

    private int outputLength = 0;

    @Override
    public void onMessage(Event<Message> event) throws Exception {
        int l = 70;
        Message message = event.getMessage();
        if (message.hasData()) {
            if (!" [DONE]".equals(message.getData())) {
                Map<String, Object> data = message.jsonDataToEntity(new SerializationTypeToken<Map<String, Object>>() {
                });
                ConfigurationMap configMap = new ConfigurationMap(data);
                String output = configMap.getString("choices[0].delta.content");
                int length = output.length();
                int rem = outputLength % l;
                outputLength += output.length();

                int j = l - rem;
                while (true) {
                    if (j > length) {
                        break;
                    }

                    output = output.substring(0, j) + "\n" + output.substring(j);
                    length++;
                    j += l;
                }

                for (char c : output.toCharArray()) {
                    System.out.print(c);
                    Thread.sleep(50L);
                }

            } else {
                outputLength = 0;
                System.out.println("\n");
            }
        }
    }
}
