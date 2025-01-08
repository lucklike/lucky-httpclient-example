package io.github.lucklike.luckyclient.api.cairh.itrus;

import com.luckyframework.httpclient.core.meta.Request;
import com.luckyframework.httpclient.proxy.annotations.DomainName;
import com.luckyframework.httpclient.proxy.spel.SpELImport;
import com.luckyframework.httpclient.proxy.spel.hook.Lifecycle;
import com.luckyframework.httpclient.proxy.spel.hook.callback.Callback;
import io.github.lucklike.httpclient.annotation.HttpClient;
import org.springframework.core.env.Environment;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static com.luckyframework.httpclient.proxy.CommonFunctions.aes;
import static com.luckyframework.httpclient.proxy.CommonFunctions.base64;
import static com.luckyframework.httpclient.proxy.CommonFunctions.sha1;
import static com.luckyframework.httpclient.proxy.CommonFunctions.sha3224;

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@SpELImport(ItrusHttpApi.ItrusAddCommonParamFunction.class)
@HttpClient("${cairh.itrus.url}")
public @interface ItrusHttpApi {


    /**
     * 用于添加公共参数的函数
     */
    class ItrusAddCommonParamFunction {

        @Callback(lifecycle = Lifecycle.REQUEST)
        static void addCommonParam(Request request, Environment env) throws Exception {

            String appSecret = env.getProperty("cairh.itrus.appSecret");
            String serviceCode = env.getProperty("cairh.itrus.serviceCode");
            String key = appSecret + serviceCode;
            String bodyStr = request.getBody().getBodyAsString();

            request.addHeader("Content-Signature", "HMAC-SHA1 " + base64(sha1(key, bodyStr)));
            request.addHeader("appId", env.getProperty("cairh.itrus.apiId"));
            request.addHeader("serviceCode", serviceCode);
            request.addHeader("Accept", "application/json; charset=utf-8");
            request.addHeader("companyUUID", env.getProperty("cairh.itrus.companyUUID"));

        }
    }
}

