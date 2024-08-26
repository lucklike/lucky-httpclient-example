package io.github.lucklike.luckyclient.api.websocket;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.net.InetSocketAddress;
import java.net.UnknownHostException;

/**
 * WebSocket工具： http://coolaf.com/tool/chattest
 */
public class MyWebSocketServer extends WebSocketServer {

    public MyWebSocketServer(int port) throws UnknownHostException {
        super(new InetSocketAddress(port));
    }

    public MyWebSocketServer(InetSocketAddress address) {
        super(address);
    }

    @Override
    public void onOpen(WebSocket webSocket, ClientHandshake clientHandshake) {
        webSocket.send("Welcome to the server!"); // This method sends a message to the new client
        broadcast("new connection: " + clientHandshake
                .getResourceDescriptor()); // This method sends a message to all clients connected
        System.out.println(
                webSocket.getRemoteSocketAddress().getAddress().getHostAddress() + " entered the room!");
    }

    @Override
    public void onClose(WebSocket webSocket, int i, String s, boolean b) {
        broadcast(webSocket + " has left the room!");
        System.out.println(webSocket + " has left the room!");
    }

    @Override
    public void onMessage(WebSocket webSocket, String message) {

        broadcast(message);
        System.out.println(webSocket + ": " + message);
    }

    @Override
    public void onError(WebSocket webSocket, Exception e) {
        e.printStackTrace();
        if (webSocket != null) {
            // some errors like port binding failed may not be assignable to a specific
            // websocket
        }
    }

    @Override
    public void onStart() {
        System.out.println("Server started!");
        setConnectionLostTimeout(0);
        setConnectionLostTimeout(100);
    }
}
