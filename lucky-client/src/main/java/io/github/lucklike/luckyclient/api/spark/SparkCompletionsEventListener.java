package io.github.lucklike.luckyclient.api.spark;


import com.luckyframework.common.Console;
import com.luckyframework.httpclient.proxy.sse.AnnotationEventListener;
import com.luckyframework.httpclient.proxy.sse.OnMessage;
import com.luckyframework.reflect.Param;
import io.github.lucklike.luckyclient.api.util.DelayedOutput;

import java.util.HashMap;
import java.util.Map;

public class SparkCompletionsEventListener extends AnnotationEventListener {

    private final Map<Integer, String> errCodeMap = new HashMap<Integer, String>() {{
        put(11200, "授权错误：该appId没有相关功能的授权 或者 业务量超过限制!");
        put(10007, "用户流量受限：服务正在处理用户当前的问题，需等待处理完成后再发送新的请求。（必须要等大模型完全回复之后，才能发送下一个问题）!");
        put(10013, "输入内容审核不通过，涉嫌违规，请重新调整输入内容");
        put(10014, "输出内容涉及敏感信息，审核不通过，后续结果无法展示给用户");
        put(10019, "本次会话内容有涉及违规信息的倾向；建议开发者收到此错误码后给用户一个输入涉及违规的提示");
        put(10907, "token数量超过上限。对话历史+问题的字数太多，需要精简输入");
        put(11201, "授权错误：日流控超限。超过当日最大访问量的限制");
        put(11202, "授权错误：秒级流控超限。秒级并发超过授权路数限制");
        put(11203, "授权错误：并发流控超限。并发路数超过授权路数限制");
    }};


    @OnMessage("#{($data$ eq ' [DONE]') or (#nonText($data$))}")
    public void onClear() {
        DelayedOutput.clearOutputLength();
        System.out.println("\n");
    }

    @OnMessage("#{$JData$.code == 0}")
    public void contentPrint(@Param("#{$JData$.choices[0].delta.content}") String content) {
        DelayedOutput.output(content, 70, 40);
    }

    @OnMessage("#{$JData$.code != 0}")
    public void errorPrint(@Param("#{$JData$.code}") int code, boolean[] booleans) {
        Console.printRed(errCodeMap.getOrDefault(code, "未知错误码：" + code));
    }

}
