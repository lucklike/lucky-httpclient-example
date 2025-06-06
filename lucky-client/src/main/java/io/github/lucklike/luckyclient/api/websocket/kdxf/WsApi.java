package io.github.lucklike.luckyclient.api.websocket.kdxf;

import com.luckyframework.httpclient.proxy.annotations.ObjectGenerate;
import com.luckyframework.httpclient.proxy.annotations.Post;
import com.luckyframework.httpclient.proxy.annotations.StaticQuery;
import com.luckyframework.httpclient.proxy.mock.MockMeta;
import com.luckyframework.httpclient.proxy.spel.SpELImport;
import io.github.lucklike.httpclient.discovery.HttpClient;
import io.github.lucklike.luckyclient.api.websocket.wapper.WebSocketMockResponseFactory;

@HttpClient("#{@kdxfConfig.wsUrl}")
@StaticQuery("zaiauth = #{@kdxfTokenManager.getToken().getAccessToken()}")
public interface WsApi {


    @SpELImport(root = "sid = #{#nanoid(16)}")
    @MockMeta(mock = @ObjectGenerate(WebSocketMockResponseFactory.class))
    @Post("/speech/service/asr/#{sid}?appId=CRH#{#nanoid(4)}&bizId=#{sid}&bizName=WebSocket&lan=chin&sr=16000&bps=16&fs=4096")
    String parseAudioFile(byte[] wavData);
}
