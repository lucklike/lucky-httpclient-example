package io.github.lucklike.luckyclient.api.cairh.hehe;

import com.luckyframework.common.ConfigurationMap;
import com.luckyframework.common.StringUtils;
import com.luckyframework.httpclient.core.meta.Request;
import com.luckyframework.httpclient.core.meta.Response;
import com.luckyframework.httpclient.generalapi.describe.ApiDescribe;
import com.luckyframework.httpclient.generalapi.describe.DescribeFunction;
import com.luckyframework.httpclient.proxy.context.MethodContext;
import com.luckyframework.httpclient.proxy.handle.HttpExceptionHandle;
import com.luckyframework.httpclient.proxy.interceptor.ErrStatusHandleInterceptor;
import com.luckyframework.httpclient.proxy.interceptor.InterceptorContext;
import io.github.lucklike.luckyclient.api.cairh.BizException;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;

@Slf4j
public class HeheStandardExceptionHandler implements ErrStatusHandleInterceptor, HttpExceptionHandle {


    @Override
    public boolean isErrStatus(int status) {
        return status != 200;
    }

    @Override
    public void handleErrStatus(Response response, InterceptorContext context) {
        ApiDescribe apiDesc = getApiDescribe(context);
        Request request = response.getRequest();
        log.error("[{}]<{}>({})接口HTTP状态码异常：'{}' {}, {}",
                request.getRequestMethod(),
                apiDesc.getName(),
                request.getURL().getPath(),
                response.getStatus(),
                response.getStringResult(),
                ! StringUtils.hasText(apiDesc.getAuthor()) ? "" : (",请联系维护人员：" + (StringUtils.hasText(apiDesc.getContactWay()) ? apiDesc.getAuthor() + "/" + apiDesc.getContactWay() : apiDesc.getAuthor()))
        );
        throw new BizException("【{}】接口HTTP状态码异常：{}", apiDesc.getName(), response.getStatus());
    }

    @Override
    public boolean isErrRespCode(Response response, InterceptorContext context) {
        List<String> errCodeList = Arrays.asList("40001", "40002", "40003", "40004", "40005", "40006", "40007", "40008", "40009", "40010", "50001", "50004", "90099");
        ConfigurationMap body = response.getConfigMapResult();

        String errorCode = body.getString("error_code");
        if (StringUtils.hasText(errorCode)) {
            return errCodeList.contains(errorCode);
        }

        return body.getInt("result") != 0;
    }

    @Override
    public void handleErrRespCode(Response response, InterceptorContext context) {
        ApiDescribe apiDesc = getApiDescribe(context);
        Request request = response.getRequest();
        ConfigurationMap body = response.getConfigMapResult();
        String errorCode = StringUtils.hasText(body.getString("error_code")) ? body.getString("error_code") : body.getString("result");
        String errorMsg = StringUtils.hasText(body.getString("error_msg")) ? body.getString("error_msg"): body.getString("info");
        log.error("[{}]<{}>({})接口响应码异常：'{}' {}, {}",
                request.getRequestMethod(),
                apiDesc.getName(),
                request.getURL().getPath(),
                errorCode,
                errorMsg,
                ! StringUtils.hasText(apiDesc.getAuthor()) ? "" : (",请联系维护人员：" + (StringUtils.hasText(apiDesc.getContactWay()) ? apiDesc.getAuthor() + "/" + apiDesc.getContactWay() : apiDesc.getAuthor()))
        );
        throw new BizException("【{}】接口响应码异常: {}, {}", apiDesc.getName(),errorCode, errorMsg);
    }

    @Override
    public Object exceptionHandler(MethodContext methodContext, Request request, Throwable throwable) {
        ApiDescribe apiDesc = DescribeFunction.describe(methodContext);
        if (throwable instanceof BizException) {
            throw (BizException) throwable;
        }
        throw new BizException(throwable, "【{}】接口调用失败", apiDesc.getName());
    }
}
