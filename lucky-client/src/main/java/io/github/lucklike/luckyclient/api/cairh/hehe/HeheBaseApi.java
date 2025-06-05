package io.github.lucklike.luckyclient.api.cairh.hehe;

import com.luckyframework.common.ConfigurationMap;
import com.luckyframework.common.StringUtils;
import com.luckyframework.httpclient.core.meta.Request;
import com.luckyframework.httpclient.core.meta.Response;
import com.luckyframework.httpclient.generalapi.describe.ApiDescribe;
import com.luckyframework.httpclient.generalapi.describe.DescribeFunction;
import com.luckyframework.httpclient.proxy.annotations.DomainName;
import com.luckyframework.httpclient.proxy.annotations.Retryable;
import com.luckyframework.httpclient.proxy.context.MethodContext;
import com.luckyframework.httpclient.proxy.spel.hook.Lifecycle;
import com.luckyframework.httpclient.proxy.spel.hook.callback.Callback;
import com.luckyframework.httpclient.proxy.spel.hook.callback.Var;
import io.github.lucklike.luckyclient.api.cairh.BizException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Retryable
@DomainName("#{$hehe.url}")
public interface HeheBaseApi {

    List<String> errCodeList = Arrays.asList("40001", "40002", "40003", "40004", "40005", "40006", "40007", "40008", "40009", "40010", "50001", "50004", "90099");

    @Var(lifecycle = Lifecycle.CLASS)
    Map<String, Object> $hehe = new HashMap<String, Object>() {{
        put("url", "${cpe.service.HeheHttp.url}");
        put("appId", "${cpe.service.HeheHttp.appId}");
        put("appSecret", "${cpe.service.HeheHttp.appSecret}");
    }};


    @Callback(lifecycle = Lifecycle.REQUEST)
    static void addDefParamCallback(Request request, MethodContext context) {
        if(DescribeFunction.matchId(context, "ycv1")) {
            request.addQueryParameter("app_id", context.getRootVar("$hehe.appId"));
            request.addQueryParameter("app_secret", context.getRootVar("$hehe.appSecret"));
        }
    }


    @Callback(lifecycle = Lifecycle.RESPONSE)
    static void exceptionCallback(Response response, MethodContext context) {
        ApiDescribe apiDesc = DescribeFunction.describe(context);
        Request request = response.getRequest();

        // HTTP状态码异常
        if (response.getStatus() != 200) {
            throw new BizException("[{}]<{}>({})接口HTTP状态码异常：'{}' {}, {}",
                    request.getRequestMethod(),
                    apiDesc.getName(),
                    request.getURL().getPath(),
                    response.getStatus(),
                    response.getStringResult(),
                    !StringUtils.hasText(apiDesc.getAuthor()) ? "" : (",请联系维护人员：" + (StringUtils.hasText(apiDesc.getContactWay()) ? apiDesc.getAuthor() + "/" + apiDesc.getContactWay() : apiDesc.getAuthor())));
        }

        // 接口响应码异常
        ConfigurationMap body = response.getConfigMapResult();
        String errorCode = body.getString("error_code");
        if (errCodeList.contains(errorCode)) {
            throw new BizException("[{}]<{}>({})接口响应码异常：'{}' {}, {}",
                    request.getRequestMethod(),
                    apiDesc.getName(),
                    request.getURL().getPath(),
                    errorCode,
                    body.getString("error_msg"),
                    ! StringUtils.hasText(apiDesc.getAuthor()) ? "" : (",请联系维护人员：" + (StringUtils.hasText(apiDesc.getContactWay()) ? apiDesc.getAuthor() + "/" + apiDesc.getContactWay() : apiDesc.getAuthor()))
            );
        }
    }

}
