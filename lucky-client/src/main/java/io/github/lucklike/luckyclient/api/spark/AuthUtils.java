package io.github.lucklike.luckyclient.api.spark;

import com.luckyframework.common.StringUtils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import static com.luckyframework.httpclient.proxy.CommonFunctions.base64;
import static com.luckyframework.httpclient.proxy.CommonFunctions.macSha256Base64;

/**
 * @author fukang
 * @version 1.0.0
 * @date 2024/7/15 02:21
 */
public class AuthUtils {

    // 鉴权方法
    public static String getAuthUrl(String hostUrl, String apiKey, String apiSecret) throws Exception {
        URL url = new URL(hostUrl);
        SimpleDateFormat format = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
        format.setTimeZone(TimeZone.getTimeZone("GMT"));
        String date = format.format(new Date());
        String preStr = "host: " + url.getHost() + "\n" + "date: " + date + "\n" + "POST " + url.getPath() + " HTTP/1.1";
        String sha = macSha256Base64(apiSecret,preStr);
        String authorization = String.format("api_key=\"%s\", algorithm=\"%s\", headers=\"%s\", signature=\"%s\"", apiKey, "hmac-sha256", "host date request-line", sha);
        return StringUtils.format("https://{}?authorization={}&date={}&host={}",
                url.getHost() + url.getPath(),
                base64(authorization),
                date,
                url.getHost()
        );
    }


}
