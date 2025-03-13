package io.github.lucklike.luckyclient.api.spark;

import com.luckyframework.common.StringUtils;
import com.luckyframework.httpclient.core.meta.ConfigurationMapBodyObjectFactory;
import com.luckyframework.httpclient.core.meta.Request;
import com.luckyframework.httpclient.core.meta.Response;
import com.luckyframework.httpclient.proxy.annotations.Condition;
import com.luckyframework.httpclient.proxy.annotations.Post;
import com.luckyframework.httpclient.proxy.annotations.RespConvert;
import com.luckyframework.httpclient.proxy.context.ClassContext;
import com.luckyframework.httpclient.proxy.spel.hook.Lifecycle;
import com.luckyframework.httpclient.proxy.spel.hook.callback.Callback;
import com.luckyframework.reflect.Param;
import io.github.lucklike.httpclient.discovery.HttpClient;
import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.luckyframework.httpclient.proxy.CommonFunctions._base64;
import static com.luckyframework.httpclient.proxy.CommonFunctions.nanoid;
import static io.github.lucklike.luckyclient.api.spark.AuthUtils.getAuthUrl;

/**
 * @author fukang
 * @version 1.0.0
 * @date 2025/1/12 04:09
 */
@HttpClient
@RespConvert
@Condition(assertion = "#{$status$ != 200}", exception = "讯飞火星API调用失败，异常的HTTP状态码[#{$status$}]: #{$body$.header.message}")
@Condition(assertion = "#{$body$.header.code != 0}", exception = "【图片生成】讯飞火星API调用失败，异常的CODE状态码：[#{$body$.header.code}]: #{$body$.header.message}")
public interface ImageGenerationApi {

    /**
     * 负责参数初始化的回调函数
     *
     * @param cc 类级别上下文
     * @return 调用API需要的变量
     * @throws Exception 初始化过程可能出现的异常
     */
    @Callback(lifecycle = Lifecycle.CLASS, storeOrNot = true, unfold = true)
    static Map<String, Object> initSpELVar(ClassContext cc) throws Exception {
        Map<String, Object> paramMap = new HashMap<>();
        String AppId = cc.parseExpression("${spark.appId}");
        String Url = cc.parseExpression("${spark.imageGenerate.URL}");
        String APIKey = cc.parseExpression("${spark.imageGenerate.APIKey}");
        String APISecret = cc.parseExpression("${spark.imageGenerate.APISecret}");

        paramMap.put("url", getAuthUrl(Url, APIKey, APISecret));
        paramMap.put("appId", AppId);
        return paramMap;
    }

    /**
     * 用于添加公共参数的回调函数
     *
     * @param request 请求对象
     * @param appId   APPID
     * @param content 图片内容
     */
    @Callback(lifecycle = Lifecycle.REQUEST_INIT, enable = "#{'imageGenerate' eq $method$.getName()}")
    static void addCommonParams(Request request,
                                @Param("#{appId}") String appId,
                                @Param("#{p0}") String content) {
        ConfigurationMapBodyObjectFactory jsonBodyFactory = ConfigurationMapBodyObjectFactory.json();
        jsonBodyFactory.addProperty("header.app_id", appId);
        jsonBodyFactory.addProperty("parameter.chat.domain", "general");
        jsonBodyFactory.addProperty("parameter.chat.width", 512);
        jsonBodyFactory.addProperty("parameter.chat.height", 512);
        jsonBodyFactory.addProperty("payload.message.text[0].role", "user");
        jsonBodyFactory.addProperty("payload.message.text[0].content", content);

        request.setBodyFactory(jsonBodyFactory);
    }

    /**
     * 响应转换方法
     *
     * @param response 响应对象
     * @param content  图片内容
     * @param savePath 图片保存路径
     * @throws IOException 转换过程中可能出现的异常
     */
    static void imageGenerate$Convert(Response response,
                                      @Param("#{p0}") String content,
                                      @Param("#{p1}") String savePath) throws IOException {
        String imageBase64 = response.getConfigMapResult().getString("payload.choices.text[0].content");
        FileCopyUtils.copy(_base64(imageBase64), new File(savePath, StringUtils.format("{}-{}.jpg", nanoid(8), content)));
        System.out.println("save to " + savePath);
    }

    @Post("#{url}")
    void imageGenerate(String content, String savePath);

}
