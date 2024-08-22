package io.github.lucklike.luckyclient.api.spark;

import com.luckyframework.httpclient.proxy.annotations.Branch;
import com.luckyframework.httpclient.proxy.annotations.ObjectGenerate;
import com.luckyframework.httpclient.proxy.annotations.Post;
import com.luckyframework.httpclient.proxy.annotations.PrintLogProhibition;
import com.luckyframework.httpclient.proxy.annotations.PropertiesJsonObject;
import com.luckyframework.httpclient.proxy.annotations.RespConvert;
import com.luckyframework.httpclient.proxy.annotations.StaticForm;
import com.luckyframework.httpclient.proxy.annotations.StaticHeader;
import com.luckyframework.httpclient.proxy.spel.SpELImport;
import com.luckyframework.httpclient.proxy.sse.Sse;
import io.github.lucklike.httpclient.annotation.HttpClient;
import kotlin.jvm.Throws;

import java.util.Map;

@HttpClient
@SpELImport(
    root = {"appId=${spark.appId}"},
    fun = {AuthUtils.class},
    pack = {"java.util"}
)
public interface AnnSparkOpenApi {

    /**
     * 讯飞火星-大模型问答
     *
     * @param content 提问内容
     */
    @PropertiesJsonObject({
        "model=general",
        "stream=#{true}",
        "messages[0].role=user",
        "messages[0].content=#{content}"
    })
    @StaticHeader({"Authorization: Bearer ${spark.completions.APIKey}:${spark.completions.APISecret}"})
    @Sse(listener = @ObjectGenerate(SparkCompletionsEventListener.class))
    @Post("https://spark-api-open.xf-yun.com/v1/chat/completions")
    @PrintLogProhibition
    void completions(String content);

    /**
     * 科大讯飞-身份证识别
     *
     * @param path 身份证图片路径
     * @return 识别后的文本内容
     */
    @SpELImport(root= {
        "currTime=#{(new Date().getTime() / 1000).toString()}",
        "param={\"engine_type\":\"idcard\",\"head_portrait\": \"0\",\"crop_image\": \"0\"}",
        "base64Param=#{#base64(param)}",
        "APIKey=${spark.idCard.APIKey}"
    })
    @StaticHeader({
        "X-Appid: #{appId}",
        "X-CurTime: #{currTime}",
        "X-Param: #{base64Param}",
        "X-CheckSum: #{#md5(APIKey + currTime + base64Param)}"
    })
    @StaticForm({
            "image=#{#base64(#resource(path))}"
    })
    @RespConvert(
        result = "#{$body$.data}",
        conditions = {
            @Branch(assertion = "#{$status$ != 200}", exception = "讯飞火星API调用失败，异常的HTTP状态码[#{$status$}]: #{$body$.header.message}"),
            @Branch(assertion = "#{$body$.code != '0'}", exception = "【身份证识别】讯飞火星API调用失败，异常的CODE状态码：[#{$body$.code}]: #{$body$.error_msg}")
        }
    )
    @Post("https://webapi.xfyun.cn/v1/service/v1/ocr/idcard")
    Map<String, Object> idCardOcr(String path);


    /**
     * 科大讯飞-图片生成
     *
     * @param content 图片的描述信息
     * @return 根据描述信息生成的图片
     */
    @SpELImport(root={
        "url=https://spark-api.cn-huabei-1.xf-yun.com/v2.1/tti",
        "APIKey=${spark.imageGenerate.APIKey}",
        "APISecret=${spark.imageGenerate.APISecret}"
    })
    @PropertiesJsonObject({
        "header.app_id=#{appId}",
        "parameter.chat.domain=general",
        "parameter.chat.width=#{512}",
        "parameter.chat.height=#{512}",
        "payload.message.text[0].role=user",
        "payload.message.text[0].content=#{content}"
    })
    @RespConvert(
        result = "#{$body$.payload.choices.text[0].content}",
        conditions = {
            @Branch(assertion = "#{$status$ != 200}", exception = "讯飞火星API调用失败，异常的HTTP状态码[#{$status$}]: #{$body$.header.message}"),
            @Branch(assertion = "#{$body$.header.code != 0}", exception = "【图片生成】讯飞火星API调用失败，异常的CODE状态码：[#{$body$.header.code}]: #{$body$.header.message}")
        }
    )
    @Post("#{#getAuthUrl(url, APIKey, APISecret)}")
    String imageGenerate(String content);

}
