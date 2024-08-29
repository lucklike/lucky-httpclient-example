package io.github.lucklike.luckyclient.api.websocket;

import com.luckyframework.common.DateUtils;

import java.net.URI;
import java.net.URISyntaxException;

public class ClientMain {

    public static void main(String[] args) throws URISyntaxException, InterruptedException {
        MyWebSocketClient client = new MyWebSocketClient(new URI("ws://localhost:8887/lucky"));
        client.connect();

        while (true) {
            if (client.isOpen()) {
                client.send(DateUtils.time() + " Hello World");
                Thread.sleep(2000L);
            }

        }
    }
}
