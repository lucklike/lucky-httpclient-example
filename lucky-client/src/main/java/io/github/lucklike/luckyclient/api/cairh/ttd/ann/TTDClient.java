package io.github.lucklike.luckyclient.api.cairh.ttd.ann;

import com.luckyframework.common.ConfigurationMap;
import com.luckyframework.common.StringUtils;
import com.luckyframework.httpclient.core.meta.Request;
import com.luckyframework.httpclient.core.meta.Response;
import com.luckyframework.httpclient.generalapi.HttpStatus;
import com.luckyframework.httpclient.generalapi.HttpStatusException;
import com.luckyframework.httpclient.generalapi.describe.ApiDescribe;
import com.luckyframework.httpclient.generalapi.describe.DescribeFunction;
import com.luckyframework.httpclient.proxy.annotations.Timeout;
import com.luckyframework.httpclient.proxy.context.MethodContext;
import com.luckyframework.httpclient.proxy.context.ParameterContext;
import com.luckyframework.httpclient.proxy.spel.SpELImport;
import com.luckyframework.httpclient.proxy.spel.hook.Lifecycle;
import com.luckyframework.httpclient.proxy.spel.hook.callback.Callback;
import io.github.lucklike.httpclient.discovery.HttpClient;
import io.github.lucklike.luckyclient.api.cairh.BizException;
import io.github.lucklike.luckyclient.api.cairh.ttd.TTDApi;
import io.github.lucklike.luckyclient.api.cairh.ttd.TTDConfig;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.nio.charset.StandardCharsets;
import java.security.Key;

import static com.luckyframework.httpclient.proxy.CommonFunctions._base64;
import static com.luckyframework.httpclient.proxy.CommonFunctions._json;
import static com.luckyframework.httpclient.proxy.CommonFunctions.base64;
import static com.luckyframework.httpclient.proxy.CommonFunctions.json;
import static com.luckyframework.httpclient.proxy.CommonFunctions.md5Hex;
import static com.luckyframework.httpclient.proxy.CommonFunctions.sha256Hex;
import static com.luckyframework.httpclient.proxy.CommonFunctions.time;
import static io.github.lucklike.luckyclient.api.cairh.ttd.ann.TTDClient.EncodeUtils.aesEncipherString;
import static io.github.lucklike.luckyclient.api.cairh.ttd.ann.TTDClient.EncodeUtils.decryptString;
import static io.github.lucklike.luckyclient.api.cairh.ttd.ann.TTDClient.EncodeUtils.getIv;
import static io.github.lucklike.luckyclient.api.cairh.ttd.ann.TTDClient.EncodeUtils.getKey;

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@SpELImport(TTDClient.TTDCallback.class)
@HttpClient("#{@TTDConfig.url}")
@Timeout(connectTimeoutExp = "#{@TTDConfig.connectionTimeout}", readTimeoutExp = "#{@TTDConfig.readTimeout}")
public @interface TTDClient {

    /**
     * TTD回调函数集合类
     */
    class TTDCallback {

        /**
         * 用于添加公共参数的回调函数
         *
         * @param req       当前请求对象
         * @param mc        当前方法上下文对象
         * @param api       TTD API
         * @param ttdConfig TTD配置对象
         * @return 非Token方法时会将调用Token接口所产生的token返回
         * @throws Exception 添加过程中可能会出现异常
         */
        @Callback(lifecycle = Lifecycle.REQUEST, storeOrNot = true, storeName = "$ttd_token")
        public static String addCommonParam(Request req,
                                            MethodContext mc,
                                            TTDApi api,
                                            TTDConfig ttdConfig) throws Exception {

            String key = ttdConfig.getKey();
            String iv = ttdConfig.getIv();
            String appId = ttdConfig.getAppId();

            String token = null;
            if (!mc.getApiDescribe().isTokenApi()) {
                token = api.getAccessToken();
                req.addHeader("accesstoken", token);
                req.addHeader("appid", aesEncipherString(getKey(token), getIv(token), appId));
                req.addHeader("serialnumber", time());

                ConfigurationMap cm = new ConfigurationMap();
                for (ParameterContext pc : mc.getParameterContexts()) {
                    TTDJForm ttdJFAnn = pc.getMergedAnnotationCheckParent(TTDJForm.class);
                    if (ttdJFAnn != null) {
                        if (pc.isSimpleBaseType()) {
                            cm.put(pc.getName(), pc.getValue());
                        } else {
                            cm = new ConfigurationMap(pc.getValue());
                        }
                    }
                }
                String json = json(cm);
                req.addFormParameter("body", aesEncipherString(getKey(token), getIv(appId), json));
                req.addHeader("cm", md5Hex(json));
            } else {
                req.addFormParameter("appid", aesEncipherString(getKey(key), getIv(iv), appId));
            }
            return token;
        }


        /**
         * 响应解码与校验
         *
         * @param mc        当前方法上下文对象
         * @param resp      当前响应对象
         * @param ttdConfig TTD配置对象
         * @return 解码之后的响应体
         */
        @Callback(lifecycle = Lifecycle.RESPONSE, storeOrNot = true, storeName = "$ttd_data")
        public static Object responseDecryptAndCheck(MethodContext mc,
                                                     Response resp,
                                                     TTDConfig ttdConfig) throws Exception {
            ApiDescribe apiDesc = DescribeFunction.describe(mc);
            Request request = resp.getRequest();

            // HTTP状态码异常
            HttpStatus status = HttpStatus.getStatus(resp.getStatus());
            if (status.isErr()) {
                throw new HttpStatusException(
                        "Http Status Error! Status:{}, Api: {}, [{}] {}",
                        status.getCode(),
                        apiDesc.getName(),
                        request.getRequestMethod(),
                        request.getUrl());
            }

            // 校验响应体
            String stringResult = resp.getStringResult();
            if (!StringUtils.hasText(stringResult)) {
                throw new BizException(
                        "An empty response body. api: {}, [{}] {}",
                        apiDesc.getName(),
                        request.getRequestMethod(),
                        request.getUrl());
            }

            // 解码并校验接口响应码，校验通过之后将解码后的响应体存入上下文中
            String key = ttdConfig.getKey();
            String iv = ttdConfig.getIv();
            String appId = ttdConfig.getAppId();

            ConfigurationMap decryptRespMap;
            if (!mc.getApiDescribe().isTokenApi()) {
                String token = mc.getRootVar("$ttd_token", String.class);
                decryptRespMap = _json(decryptString(getKey(token), getIv(appId), resp.getStringResult()), ConfigurationMap.class);
            } else {
                decryptRespMap = _json(decryptString(getKey(key), getIv(iv), resp.getStringResult()), ConfigurationMap.class);
            }

            if (decryptRespMap.getInt("code") != 0) {
                throw new BizException(
                        "The interface status code is error!, [code: {}], [api: {}], [message: {}], [{}] {}",
                        decryptRespMap.getInt("code"),
                        apiDesc.getName(),
                        decryptRespMap.getString("msg"),
                        request.getRequestMethod(),
                        request.getUrl()
                );
            }

            return decryptRespMap.getMap("data");
        }

    }


    /**
     * 报文的加密与解密工具类
     */
    class EncodeUtils {

        public static String getIv(String key) throws Exception {
            return sha256Hex(getKey(key) + key).substring(0, 16);
        }

        public static String getKey(String iv) throws Exception {
            return sha256Hex(iv).substring(0, 16);
        }

        public static String aesEncipherString(String key, String iv, String body) throws Exception {
            String enc = base64(aesEncipherByte(key, iv, body));
            enc = base64(enc.getBytes());
            return enc.replaceAll("[\\s*\t\n\r]", "");
        }

        public static String decryptString(String key, String iv, String body) throws Exception {
            SecretKeySpec secreKey = getSecretKey(key.getBytes());
            return new String(decrypt(_base64(_base64(body.getBytes())), secreKey, iv), StandardCharsets.UTF_8);
        }

        private static byte[] decrypt(byte[] data, Key key, String iv) throws Exception {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(2, key, new IvParameterSpec(iv.getBytes()));
            return cipher.doFinal(data);
        }

        private static byte[] aesEncipherByte(String key, String iv, String data) throws Exception {
            SecretKeySpec secreKey = getSecretKey(key.getBytes());
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec ivP = new IvParameterSpec(iv.getBytes());
            cipher.init(1, secreKey, ivP);
            return cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));
        }

        private static SecretKeySpec getSecretKey(byte[] key) {
            return new SecretKeySpec(key, "AES");
        }
    }
}
