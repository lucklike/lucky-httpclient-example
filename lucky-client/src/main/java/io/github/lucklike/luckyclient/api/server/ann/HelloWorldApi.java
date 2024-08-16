package io.github.lucklike.luckyclient.api.server.ann;

import com.luckyframework.httpclient.core.meta.DefaultHttpHeaderManager;
import com.luckyframework.httpclient.core.meta.HttpHeaderManager;
import com.luckyframework.httpclient.core.meta.Request;
import com.luckyframework.httpclient.core.meta.Response;
import com.luckyframework.httpclient.core.meta.ResponseMetaData;
import com.luckyframework.httpclient.proxy.annotations.Get;
import com.luckyframework.httpclient.proxy.annotations.ObjectGenerate;
import com.luckyframework.httpclient.proxy.mock.MockContext;
import com.luckyframework.httpclient.proxy.mock.MockMeta;
import com.luckyframework.httpclient.proxy.mock.MockResponseFactory;
import io.github.lucklike.entity.response.Result;
import io.github.lucklike.httpclient.annotation.HttpClientComponent;
import org.springframework.core.io.InputStreamSource;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * @author fukang
 * @version 1.0.0
 * @date 2024/7/7 03:35
 */
@HttpClientComponent
public interface HelloWorldApi extends LuckyServerApi {


    @MockMeta(mockResp = @ObjectGenerate(MyMock.class))
    @Get("/hello")
    String hello();

    @Get("/exception")
    Result<Object> exception();

    class MyMock implements MockResponseFactory {

        @Override
        public Response createMockResponse(Request request, MockContext context) {
            return new Response() {
                @Override
                public Request getRequest() {
                    return request;
                }

                @Override
                public int getStatus() {
                    return 200;
                }

                @Override
                public HttpHeaderManager getHeaderManager() {
                    return new DefaultHttpHeaderManager();
                }

                @Override
                public byte[] getResult() {
                    return "HELLO WORLD".getBytes();
                }

                @Override
                public InputStream getInputStream() {
                    return new ByteArrayInputStream(getResult());
                }

                @Override
                public ResponseMetaData getResponseMetaData() {
                    return new ResponseMetaData(getRequest(), getStatus(), getHeaderManager(), () -> getInputStream());
                }

                @Override
                public void closeResource() {

                }
            };
        }
    }
}
