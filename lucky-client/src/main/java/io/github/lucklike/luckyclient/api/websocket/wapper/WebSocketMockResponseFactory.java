package io.github.lucklike.luckyclient.api.websocket.wapper;

import com.luckyframework.httpclient.core.meta.Request;
import com.luckyframework.httpclient.core.meta.Response;
import com.luckyframework.httpclient.proxy.mock.MockContext;
import com.luckyframework.httpclient.proxy.mock.MockResponse;
import com.luckyframework.httpclient.proxy.mock.MockResponseFactory;
import io.github.lucklike.luckyclient.api.websocket.kdxf.AsrWsClient;

import java.net.URI;

public class WebSocketMockResponseFactory implements MockResponseFactory {


    @Override
    public Response createMockResponse(Request request, MockContext context) {
        try {
            AsrWsClient asrWsClient = new AsrWsClient(URI.create(request.getUrl()));
            byte[] bytes = (byte[]) context.getContext().getArguments()[0];
            return MockResponse.create().request(request).body(asrWsClient.parseAudioFile(bytes));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
