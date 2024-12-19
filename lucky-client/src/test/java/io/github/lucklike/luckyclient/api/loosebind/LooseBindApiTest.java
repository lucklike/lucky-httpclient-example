package io.github.lucklike.luckyclient.api.loosebind;

import com.luckyframework.httpclient.core.meta.DefaultRequest;
import com.luckyframework.httpclient.core.meta.Request;
import com.luckyframework.httpclient.proxy.configapi.ConfigApi;
import io.github.lucklike.luckyclient.LuckyClientApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ResolvableType;
import org.springframework.core.env.Environment;

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

    @Resource
    private Environment environment;

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
    void springBinderTest() {
        Binder binder = Binder.get(environment);
        Bindable<ConfigApi> objectBindable = Bindable.of(ResolvableType.forType(ConfigApi.class));
        BindResult<ConfigApi> bound = binder.bind("cpe.service.common-t2", objectBindable);
        ConfigApi configApi = bound.get();
        System.out.println(configApi);
    }

    @Test
    void t3Test() {
        System.out.println(commonT3ServiceApi.test(new T3Request()));
    }

    public static void main(String[] args) throws URISyntaxException {
        DefaultRequest request = Request.get("ws://localhost:88080/lucky");
        System.out.println(new URI(request.getUrl()));

    }
}