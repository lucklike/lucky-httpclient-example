package io.github.lucklike.luckyclient.api.cairh.itrus;

import com.luckyframework.common.ConfigurationMap;
import com.luckyframework.httpclient.core.meta.Request;
import com.luckyframework.httpclient.core.meta.Response;
import com.luckyframework.httpclient.generalapi.describe.ApiDescribe;
import com.luckyframework.httpclient.generalapi.describe.DescribeFunction;
import com.luckyframework.httpclient.proxy.annotations.RespConvert;
import com.luckyframework.httpclient.proxy.context.MethodContext;
import com.luckyframework.httpclient.proxy.spel.Namespace;
import com.luckyframework.httpclient.proxy.spel.SpELImport;
import com.luckyframework.httpclient.proxy.spel.hook.Lifecycle;
import com.luckyframework.httpclient.proxy.spel.hook.callback.Callback;
import io.github.lucklike.httpclient.annotation.HttpClient;
import io.github.lucklike.luckyclient.api.cairh.BizException;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static com.luckyframework.httpclient.proxy.CommonFunctions.base64;
import static com.luckyframework.httpclient.proxy.CommonFunctions.sha1;

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@RespConvert(resultFunc = "itrus_autoConvert")
@SpELImport(ItrusHttpApi.ItrusFunctions.class)
@HttpClient("#{@itrusCommonParam.url}")
public @interface ItrusHttpApi {


    /**
     * 用于添加公共参数的函数
     */
    @Namespace("itrus")
    class ItrusFunctions {

        @Callback(lifecycle = Lifecycle.REQUEST)
        static void addCommonParam(Request request, ItrusCommonParam commonParam) throws Exception {
            String bodyStr = request.getBody().getBodyAsString();
            String key = commonParam.getAppSecret() + commonParam.getServiceCode();
            request.addHeader("Content-Signature", "HMAC-SHA1 " + base64(sha1(key, bodyStr)));
            request.addHeader("appId", commonParam.getAppId());
            request.addHeader("serviceCode", commonParam.getServiceCode());
            request.addHeader("Accept", "application/json; charset=utf-8");
            request.addHeader("companyUUID", commonParam.getCompanyUUID());
        }

        /**
         * 响应类型转换
         *
         * @param context  上下文
         * @param response 响应对象
         * @return 目标对象
         */
        static Object autoConvert(MethodContext context, Response response) {
            ApiDescribe desc = DescribeFunction.describe(context);
            int status = response.getStatus();
            if (status != 200) {
                throw new BizException("【{}】接口调用异常：http-status={}", desc.getName(), status);
            }

            ConfigurationMap configMap = response.getConfigMapResult();
            if (1 != configMap.getInt("status")) {
                throw new BizException("【{}】接口调用异常，status={}, message={}", desc.getName(), configMap.getInt("status"), configMap.getString("message"));
            }

            return configMap.getEntry("data", context.getRealMethodResolvableType());
        }
    }
}

