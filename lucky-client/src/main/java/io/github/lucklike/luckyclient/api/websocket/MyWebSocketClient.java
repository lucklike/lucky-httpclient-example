package io.github.lucklike.luckyclient.api.websocket;

import lombok.extern.slf4j.Slf4j;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;

@Slf4j
public class MyWebSocketClient extends WebSocketClient {
    public MyWebSocketClient(URI serverUri) {
        super(serverUri);
    }

    @Override
    public void onOpen(ServerHandshake serverHandshake) {
        log.info("与服务端建立连接");
    }

    @Override
    public void onMessage(String s) {
        log.info("收到服务端的消息：{}", s);
    }

    @Override
    public void onClose(int i, String s, boolean b) {
        log.info("与服务端的连接断开 code:{} reason:{} {}", i, s, b);
    }

    @Override
    public void onError(Exception e) {
        log.info("连接报错");
    }
}
