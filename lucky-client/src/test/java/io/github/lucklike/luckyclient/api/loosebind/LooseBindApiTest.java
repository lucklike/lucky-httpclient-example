package io.github.lucklike.luckyclient.api.loosebind;

import com.luckyframework.httpclient.core.meta.DefaultRequest;
import com.luckyframework.httpclient.core.meta.Request;
import com.luckyframework.httpclient.proxy.annotations.BodyParam;
import com.luckyframework.httpclient.proxy.annotations.DynamicParam;
import com.luckyframework.httpclient.proxy.annotations.JsonBody;
import com.luckyframework.reflect.AnnotationUtils;
import io.github.lucklike.httpclient.configapi.LocalConfigHttpClient;
import io.github.lucklike.luckyclient.LuckyClientApplication;
import io.github.lucklike.luckyclient.api.baiduai.BaiduAI;
import io.github.lucklike.luckyclient.api.util.AI;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.net.URI;
import java.net.URISyntaxException;

@SpringBootTest(classes = LuckyClientApplication.class)
class LooseBindApiTest {

    @Resource
    private LooseBindApi looseBindApi;

    @Resource
    private CommonT2ServiceApi commonT2ServiceApi;

    @Resource
    private CommonT3ServiceApi commonT3ServiceApi;

    @Test
    void book() {
        System.out.println(looseBindApi.book());
    }

    @Test
    void bindTest() {
        System.out.println(commonT2ServiceApi.test(new BaseParam()));
    }

    @Test
    void queryTest() {
        System.out.println(commonT2ServiceApi.queryTest(new BaseParam(), new BaseParam()));
    }

    @Test
    void t3Test() {
        System.out.println(commonT3ServiceApi.test());
    }

    public static void main(String[] args) throws URISyntaxException {
        DefaultRequest request = Request.get("ws://localhost:88080/lucky");
        System.out.println(new URI(request.getUrl()));

    }
}