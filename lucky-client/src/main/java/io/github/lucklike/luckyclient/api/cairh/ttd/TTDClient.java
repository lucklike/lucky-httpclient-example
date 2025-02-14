package io.github.lucklike.luckyclient.api.cairh.ttd;

import com.luckyframework.common.ConfigurationMap;
import com.luckyframework.httpclient.core.meta.Request;
import com.luckyframework.httpclient.core.meta.Response;
import com.luckyframework.httpclient.proxy.annotations.RespConvert;
import com.luckyframework.httpclient.proxy.annotations.Timeout;
import com.luckyframework.httpclient.proxy.context.MethodContext;
import com.luckyframework.httpclient.proxy.context.ParameterContext;
import com.luckyframework.httpclient.proxy.spel.FunctionFilter;
import com.luckyframework.httpclient.proxy.spel.SpELImport;
import com.luckyframework.httpclient.proxy.spel.hook.Lifecycle;
import com.luckyframework.httpclient.proxy.spel.hook.callback.Callback;
import io.github.lucklike.httpclient.annotation.HttpClient;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Value;

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
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

import static com.luckyframework.httpclient.proxy.CommonFunctions._json;
import static com.luckyframework.httpclient.proxy.CommonFunctions.json;
import static com.luckyframework.httpclient.proxy.CommonFunctions.time;

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@RespConvert(resultFunc = "ttdConvert")
@SpELImport(TTDClient.TTDFunction.class)
@HttpClient("#{@TTDConfig.url}")
@Timeout(connectionTimeoutExp = "#{@TTDConfig.connectionTimeout}", readTimeoutExp = "#{@TTDConfig.readTimeout}")
public @interface TTDClient {

    class TTDFunction {

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
            String methodName = mc.getCurrentAnnotatedElement().getName();
            if (Objects.equals(methodName, "getAccessToken")) {
                req.addFormParameter("appid", aesEncipherString(getKey(key), getIv(iv), appId));
            } else {
                token = api.getAccessToken();
                req.addHeader("accesstoken", token);
                req.addHeader("appid", aesEncipherString(getKey(token), getIv(token), appId));
                req.addHeader("serialnumber", time());

                Map<String, Object> argsMap = new LinkedHashMap<>();
                for (ParameterContext pc : mc.getParameterContexts()) {
                    argsMap.put(pc.getName(), pc.getValue());
                }
                String json = json(argsMap);
                req.addFormParameter("body", aesEncipherString(getKey(token), getIv(appId), json));
                req.addHeader("cm", DigestUtils.md5Hex(json));
            }
            return token;
        }

        /**
         * 接口响应转换逻辑
         *
         * @param resp      响应对象
         * @param mc        当前方法上下文对象
         * @param ttdConfig TTD配置对象
         * @return 转换后的结果
         * @throws Exception 转换过程中可能会出现的异常
         */
        public static Object ttdConvert(Response resp,
                                        MethodContext mc,
                                        TTDConfig ttdConfig) throws Exception {

            String key = ttdConfig.getKey();
            String iv = ttdConfig.getIv();
            String appId = ttdConfig.getAppId();

            String methodName = mc.getCurrentAnnotatedElement().getName();
            if (Objects.equals(methodName, "getAccessToken")) {
                ConfigurationMap configMap = _json(decryptString(getKey(key), getIv(iv), resp.getStringResult()), ConfigurationMap.class);
                return configMap.getString("data.bean.access_token");
            }
            String token = mc.getRootVar("$ttd_token", String.class);
            return _json(decryptString(getKey(token), getIv(appId), resp.getStringResult()), mc.getRealMethodReturnResolvableType());
        }


        @FunctionFilter
        public static String getIv(String key) {
            return DigestUtils.sha256Hex(getKey(key) + key).substring(0, 16);
        }

        @FunctionFilter
        public static String getKey(String iv) {
            return DigestUtils.sha256Hex(iv).substring(0, 16);
        }

        @FunctionFilter
        public static String aesEncipherString(String key, String iv, String body) throws Exception {
            String enc = Base64.encodeBase64String(aesEncipherByte(key, iv, body));
            enc = Base64.encodeBase64String(enc.getBytes());
            return enc.replaceAll("[\\s*\t\n\r]", "");
        }

        @FunctionFilter
        public static String decryptString(String key, String iv, String body) throws Exception {
            SecretKeySpec secreKey = getSecretKey(key.getBytes());
            return new String(decrypt(Base64.decodeBase64(Base64.decodeBase64(body.getBytes())), secreKey, iv), "UTF-8");
        }

        @FunctionFilter
        private static byte[] decrypt(byte[] data, Key key, String iv) throws Exception {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(2, key, new IvParameterSpec(iv.getBytes()));
            return cipher.doFinal(data);
        }

        @FunctionFilter
        private static byte[] aesEncipherByte(String key, String iv, String data) throws Exception {
            SecretKeySpec secreKey = getSecretKey(key.getBytes());
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec ivP = new IvParameterSpec(iv.getBytes());
            cipher.init(1, secreKey, ivP);
            return cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));
        }

        @FunctionFilter
        private static SecretKeySpec getSecretKey(byte[] key) {
            return new SecretKeySpec(key, "AES");
        }
    }
}
