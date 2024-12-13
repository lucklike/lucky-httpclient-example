package io.github.lucklike.luckyclient.api.cairh.function;

import com.luckyframework.common.ConfigurationMap;
import com.luckyframework.conversion.ConversionUtils;
import com.luckyframework.httpclient.core.meta.Request;
import com.luckyframework.httpclient.core.meta.Response;
import com.luckyframework.httpclient.generalapi.describe.DescribeFunction;
import com.luckyframework.httpclient.proxy.CommonFunctions;
import com.luckyframework.httpclient.proxy.context.MethodContext;
import com.luckyframework.httpclient.proxy.spel.FunctionAlias;
import com.luckyframework.httpclient.proxy.spel.Namespace;
import com.luckyframework.httpclient.proxy.spel.hook.Lifecycle;
import com.luckyframework.httpclient.proxy.spel.hook.callback.Callback;
import com.luckyframework.httpclient.proxy.spel.hook.callback.Var;
import io.github.lucklike.luckyclient.api.cairh.BizException;
import io.github.lucklike.luckyclient.api.cairh.annotations.LooseBind;
import io.github.lucklike.luckyclient.api.cairh.openapi.CrhOpenApi;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Objects;

/**
 * CRH-OPENAPI公共方法
 */
@Namespace("crh")
public class CairhCommonFunction {

    /**
     * 构建代理对象时生成URL
     */
    @Var(lifecycle = Lifecycle.CLASS)
    public static final String $crh_base_url = "${cairh.openapi.url}/#{#ann($cc$, 'io.github.lucklike.luckyclient.api.cairh.annotations.CRHApi').project}";


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

//    /**
//     * CRH开放API检验的回调函数，运行非CRH API时会抛异常
//     *
//     * @param mc      方法上下文
//     * @param request 请求对象
//     */
//    @Callback(lifecycle = Lifecycle.REQUEST)
//    public static Object crhApiCheck(MethodContext mc, Request request) {
//        String cairhOpenApi = mc.parseExpression(URL_CONFIG, String.class);
//        if (!request.getUrl().startsWith(cairhOpenApi)) {
//            return new IllegalArgumentException("Not CRH Open Platform API: " + request.getUrl());
//        }
//        return null;
//    }

    /**
     * 设置默认参数的回调函数
     *
     * @param mc       方法上下文
     * @param request  请求对象
     * @param openApi 获取Token的类对象
     */
    @Callback(lifecycle = Lifecycle.REQUEST)
    public static void addDefParamCallback(
            MethodContext mc,
            Request request,
            @Qualifier("crhOpenApi") CrhOpenApi openApi
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
            return new BizException("【财人汇】开放接口访问失败！HTTP状态码：{}， 接口地址： [{}]{}", status, req.getRequestMethod(), req.getUrl());
        }

        //响应码异常
        ConfigurationMap body = resp.getConfigMapResult();
        String errorNo = body.getString("error.error_no");
        if (!Objects.equals("0", errorNo)) {
            return new BizException("【财人汇】开放接口访问失败！接口响应码：{}, 错误信息：{}，接口地址： [{}]{}", errorNo, body.getString("error.error_info"), req.getRequestMethod(), req.getUrl());
        }

        return null;
    }
}
