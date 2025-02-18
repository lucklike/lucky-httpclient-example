package io.github.lucklike.luckyclient.api.cairh.nacos;

import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.luckyframework.common.ConfigurationMap;
import com.luckyframework.common.StringUtils;
import com.luckyframework.httpclient.core.meta.Request;
import com.luckyframework.httpclient.core.meta.Response;
import com.luckyframework.httpclient.generalapi.describe.DescribeFunction;
import com.luckyframework.httpclient.proxy.CommonFunctions;
import com.luckyframework.httpclient.proxy.annotations.DomainNameMeta;
import com.luckyframework.httpclient.proxy.annotations.ObjectGenerate;
import com.luckyframework.httpclient.proxy.annotations.RespConvert;
import com.luckyframework.httpclient.proxy.context.MethodContext;
import com.luckyframework.httpclient.proxy.spel.FunctionAlias;
import com.luckyframework.httpclient.proxy.spel.SpELImport;
import com.luckyframework.httpclient.proxy.spel.hook.Lifecycle;
import com.luckyframework.httpclient.proxy.spel.hook.callback.Callback;
import com.luckyframework.httpclient.proxy.url.DomainNameContext;
import com.luckyframework.httpclient.proxy.url.DomainNameGetter;
import io.github.lucklike.httpclient.annotation.HttpClientComponent;
import io.github.lucklike.luckyclient.api.cairh.BizException;
import io.github.lucklike.luckyclient.api.cairh.annotations.LooseBind;
import io.github.lucklike.luckyclient.nacos.NacosConfigProperties;
import io.github.lucklike.luckyclient.nacos.ServiceInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Objects;

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@HttpClientComponent
@RespConvert(resultFunc = "convert")
@SpELImport({NacosClient.CallbackAndFunction.class})
@DomainNameMeta(getter = @ObjectGenerate(NacosClient.NacosDomainGetter.class))
public @interface NacosClient {

    /**
     * 协议（http or https）
     */
    String protocol() default "http";

    /**
     * 服务名
     */
    String value();

    /**
     * context-path
     */
    String contextPath() default "";


    /**
     * nacos扩展组件，根据nacos服务名获取服务IP端口等信息
     */
    @Component
    class NacosDomainGetter implements DomainNameGetter {
        @Autowired
        @Qualifier("crhNamingService")
        private NamingService nacosService;

        @Autowired
        @Qualifier("crhNacosConfigProperties")
        private NacosConfigProperties config;


        @Override
        public String getDomainName(DomainNameContext context) throws NacosException {
            NacosClient nacosAnn = context.toAnnotation(NacosClient.class);

            String protocol = nacosAnn.protocol();
            String serviceKey = nacosAnn.value();
            String contextPath = nacosAnn.contextPath();

            ServiceInfo serviceInfo = config.getServices().get(serviceKey);
            Instance instance = nacosService.selectOneHealthyInstance(serviceInfo.getServiceName(), serviceInfo.getGroup());

            return StringUtils.format("{}://{}:{}/{}", protocol, instance.getIp(), instance.getPort(), contextPath);
        }
    }

    /**
     * 回调函数以及工具函数
     */
    class CallbackAndFunction {
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
         * @param mc       方法上下文
         * @param request  请求对象
         * @param tokenApi 获取Token的类对象
         */
        @Callback(lifecycle = Lifecycle.REQUEST)
        public static void addDefParamCallback(
                MethodContext mc,
                Request request,
                CrhTokenApi tokenApi
        ) {
            if (DescribeFunction.needToken(mc)) {
                request.addHeader("Authorization", tokenApi.getAccessToken());
            }
        }

        /**
         * 检验状态码和响应码的回调函数
         *
         * @param resp 响应对象
         */
        @Callback(lifecycle = Lifecycle.RESPONSE)
        public static void errorStatusHandlerCallback(Response resp) {
            int status = resp.getStatus();
            Request req = resp.getRequest();

            // 状态码异常
            if (status != 200) {
                throw new BizException("【CRH】开放接口访问失败！HTTP状态码：{}， 接口地址： [{}]{}", status, req.getRequestMethod(), req.getUrl());
            }

            //响应码异常
            ConfigurationMap body = resp.getConfigMapResult();
            String errorNo = body.getString("error.error_no");
            if (!Objects.equals("0", errorNo)) {
                throw new BizException("【CRH】开放接口访问失败！接口响应码：{}, 错误信息：{}，接口地址： [{}]{}", errorNo, body.getString("error.error_info"), req.getRequestMethod(), req.getUrl());
            }
        }
    }
}
