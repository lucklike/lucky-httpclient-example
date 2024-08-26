package io.github.lucklike.luckyclient.api.websocket;

import java.net.URI;
import java.net.URISyntaxException;

public class ClientMain {

    public static void main(String[] args) throws URISyntaxException {
        MyWebSocketClient client = new MyWebSocketClient(new URI("ws://localhost:8887/lucky"));
        client.connect();
    }
}
