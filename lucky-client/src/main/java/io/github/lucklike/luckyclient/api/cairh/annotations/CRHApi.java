package io.github.lucklike.luckyclient.api.cairh.annotations;

import com.luckyframework.common.ConfigurationMap;
import com.luckyframework.httpclient.core.meta.Request;
import com.luckyframework.httpclient.core.meta.Response;
import com.luckyframework.httpclient.generalapi.describe.DescribeFunction;
import com.luckyframework.httpclient.proxy.CommonFunctions;
import com.luckyframework.httpclient.proxy.annotations.RespConvert;
import com.luckyframework.httpclient.proxy.annotations.SSL;
import com.luckyframework.httpclient.proxy.context.MethodContext;
import com.luckyframework.httpclient.proxy.spel.FunctionAlias;
import com.luckyframework.httpclient.proxy.spel.Namespace;
import com.luckyframework.httpclient.proxy.spel.SpELImport;
import com.luckyframework.httpclient.proxy.spel.hook.Lifecycle;
import com.luckyframework.httpclient.proxy.spel.hook.callback.Callback;
import io.github.lucklike.httpclient.discovery.HttpClient;
import io.github.lucklike.luckyclient.api.cairh.BizException;
import io.github.lucklike.luckyclient.api.cairh.openapi.CrhOpenApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Objects;

/**
 * 注解集成方式
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@SSL
@HttpClient("${cairh.openapi.url}")
@RespConvert(resultFunc = "crh_convert")
@SpELImport({CairhCommonFunction.class})
public @interface CRHApi {

    @AliasFor(annotation = HttpClient.class, attribute = "beanId")
    String name() default "";

    @AliasFor(annotation = HttpClient.class, attribute = "path")
    String project() default "";
}

/**
 * CRH-OPENAPI公共方法
 */
@Slf4j
@Namespace("crh")
class CairhCommonFunction {

    /**
     * 确定转换表达式，当方法被{@link LooseBind @LooseBind}标注时使用松散绑定
     *
     * @param mc 当前方法上下文
     * @return 转换表达式
     */
    @FunctionAlias("convert")
    public static Object convert(MethodContext mc, Response response) {
        // 方法返回值为Response类型时直接返回响应对象
        if (Response.class.isAssignableFrom(mc.getReturnResolvableType().resolve())) {
            return response;
        }

        // 根据是否被@LooseBind注解标注来决定是否启用松散绑定功能
        LooseBind looseBindAnn = mc.getSameAnnotationCombined(LooseBind.class);
        if (looseBindAnn == null || !looseBindAnn.value()) {
            return response.getConfigMapResult().getEntry("data", mc.getRealMethodReturnType());
        }
        return CommonFunctions.lb(mc, response.getConfigMapResult().getMap("data"));
    }

    /**
     * 设置默认参数的回调函数
     *
     * @param mc      方法上下文
     * @param request 请求对象
     * @param openApi 获取Token的类对象
     */
    @Callback(lifecycle = Lifecycle.REQUEST)
    public static void addDefParamCallback(
            MethodContext mc,
            Request request,
            CrhOpenApi openApi
    ) {
        if (DescribeFunction.needToken(mc)) {
            request.addHeader("Authorization", openApi.getAccessToken());
        }
    }

    /**
     * 检验状态码和响应码的回调函数
     *
     * @param resp 响应对象
     * @return 校验不通过时返回异常对象，通过时返回null
     */
    @Callback(lifecycle = Lifecycle.RESPONSE)
    public static Object errorStatusHandlerCallback(Response resp) {
        int status = resp.getStatus();
        Request req = resp.getRequest();

        // 状态码异常
        if (status != 200) {
            throw new BizException("【财人汇】开放接口访问失败！HTTP状态码：{}， 接口地址： [{}]{}", status, req.getRequestMethod(), req.getUrl());
        }

        //响应码异常
        ConfigurationMap body = resp.getConfigMapResult();
        String errorNo = body.getString("error.error_no");
        if (!Objects.equals("0", errorNo)) {
            throw new BizException("【财人汇】开放接口访问失败！接口响应码：{}, 错误信息：{}，接口地址： [{}]{}", errorNo, body.getString("error.error_info"), req.getRequestMethod(), req.getUrl());
        }

        return null;
    }
}
