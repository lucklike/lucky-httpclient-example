package io.github.lucklike.luckyclient.api.spark;

import com.luckyframework.common.Console;
import com.luckyframework.common.StringUtils;
import com.luckyframework.httpclient.proxy.configapi.EncoderUtils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * @author fukang
 * @version 1.0.0
 * @date 2024/7/15 02:21
 */
public class AuthUtils {

    /**
     * 获取RFC1123格式的时间字符串
     *
     * @return RFC1123格式的时间字符串
     */
    private static String rfc1123Date() {
        SimpleDateFormat sdf3 = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
        sdf3.setTimeZone(TimeZone.getTimeZone("GMT"));
        return sdf3.format(new Date());
    }

    private static String getTemp(String host, String path, String method, String date) {
        return "host: " + host + "\n" +
                "date: " + date + "\n" +
                method + " " + path + " HTTP/1.1";
    }

    private static String getSignature(String host, String path, String method, String date, String APISecret) throws Exception {
        String temp = getTemp(host, path, method, date);
        String signature = hmacSha256(APISecret, temp);
        Console.println("temp\n: {}\nsignature: {}", temp, signature);
        return signature;
    }

    private static String getAuthorization(String host, String path, String method, String date, String APISecret, String ApiKey) throws Exception {
        String authorizationTemp = "api_key=\"{}\", algorithm=\"hmac-sha256\", headers=\"host date request-line\", signature=\"{}\"";
        String authorizationOrigin = StringUtils.format(authorizationTemp, ApiKey, getSignature(host, path, method, date, APISecret));
        Console.println("authorization_origin: {}", authorizationOrigin);
        return EncoderUtils.base64(authorizationOrigin);
    }

    public static String getUrl(String urlStr, String method, String APISecret, String ApiKey) throws Exception {
        URL url = new URL(urlStr);
        String vTemp = "{\"authorization\": \"{}\", \"date\": \"{}\", \"host\": \"{}\"}";
        String v = StringUtils.format(vTemp, getAuthorization(url.getHost(), url.getPath(), method, rfc1123Date(), APISecret, ApiKey), rfc1123Date(), url.getHost());
        Console.println("v: {}", v);
        return url.toExternalForm() + "?authorization=" + EncoderUtils.base64(v);
    }

    private static String hmacSha256(String APISecret, String message) {
        try {
            // 获取HmacSHA256算法实例
            Mac sha256Hmac = Mac.getInstance("HmacSHA256");
            // 创建一个密钥规范
            SecretKeySpec secretKey = new SecretKeySpec(APISecret.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
            // 初始化Mac对象
            sha256Hmac.init(secretKey);

            // 计算签名
            byte[] hmacBytes = sha256Hmac.doFinal(message.getBytes(StandardCharsets.UTF_8));
            // 将签名转换为Base64编码的字符串

            // 输出签名
            return Base64.getEncoder().encodeToString(hmacBytes);
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate HMAC-SHA256", e);
        }
    }


}
