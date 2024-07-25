package io.github.lucklike.luckyclient.api.kuaitong;

import com.luckyframework.common.Resources;
import com.luckyframework.httpclient.core.meta.Request;
import com.luckyframework.httpclient.core.meta.Response;
import com.luckyframework.httpclient.proxy.context.MethodContext;
import com.luckyframework.httpclient.proxy.interceptor.Interceptor;
import com.luckyframework.httpclient.proxy.interceptor.InterceptorContext;
import io.github.lucklike.httpclient.configapi.LocalConfigHttpClient;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.Writer;

import static com.luckyframework.httpclient.core.serialization.SerializationConstant.JSON_SCHEME;

/**
 * 快瞳API
 */
@LocalConfigHttpClient
public interface KuaiTongApi {

    /**
     * 获取访问Token
     *
     * @return 访问Token
     */
    Token getAccessToken();


    /**
     * 身份证OCR识别
     *
     * @param idCardPath 身份证图片路径
     * @return 身份证信息
     */
    IdentityInfo identityCardOcr(String idCardPath);


    /**
     * 身份证OCR识别，直接返回响应对象，此方法用于token文件被损坏时，重新获取token并发重新送请求
     *
     * @param idCardPath 身份证图片路径
     * @return 身份证信息
     */
    Response identityCardOcrReturnResponse(String idCardPath);

    /**
     * 自动添加Token的拦截器，支持过期自动续签
     */
    @Component("kuaiTongAutoAddTokenInterceptor")
    class KuaiTongAutoAddTokenInterceptor implements Interceptor {

        private final String TOKEN_FILE_NAME = "kt_token.json";

        @Resource
        private KuaiTongApi api;

        private Token token;

        @PostConstruct
        public void init() {
            token = loadToken();
        }

        @Override
        public void doBeforeExecute(Request request, InterceptorContext context) {
            request.addMultipartFormParameter("token", getToken());
        }

        @Override
        public Response doAfterExecute(Response response, InterceptorContext context) {
            int status = response.getStatus();

            if (status == 401 || status == 403) {
                saveToken();
                MethodContext methodContext = context.getContext();
                KuaiTongApi api = (KuaiTongApi) methodContext.getProxyObject();
                return api.identityCardOcrReturnResponse((String) methodContext.getArguments()[0]);
            }
            return response;
        }

        private synchronized String getToken() {
            if (token == null || token.isExpires()) {
                saveToken();
            }
            return token.getAccess_token();
        }

        private void saveToken() {
            try {
                token = api.getAccessToken();
                token.generateExpiresTime();
                Writer writer = Resources.getWorkingDirectoryWriter(TOKEN_FILE_NAME);
                FileCopyUtils.copy(JSON_SCHEME.serialization(token), writer);
            } catch (Exception e) {
                throw new RuntimeException("token保存异常！", e);
            }

        }

        private Token loadToken() {
            try {
                return Resources.fromWorkingDirectoryJson(TOKEN_FILE_NAME, Token.class);
            } catch (Exception e) {
                return null;
            }
        }
    }
}
