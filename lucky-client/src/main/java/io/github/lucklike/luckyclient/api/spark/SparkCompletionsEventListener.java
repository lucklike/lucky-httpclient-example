package io.github.lucklike.luckyclient.api.spark;


import com.luckyframework.common.ConfigurationMap;
import com.luckyframework.common.Console;
import com.luckyframework.exception.LuckyRuntimeException;
import com.luckyframework.httpclient.core.meta.HeaderMataData;
import com.luckyframework.httpclient.core.meta.Response;
import com.luckyframework.httpclient.proxy.sse.Event;
import com.luckyframework.httpclient.proxy.sse.EventListener;
import com.luckyframework.httpclient.proxy.sse.Message;
import com.luckyframework.serializable.SerializationTypeToken;
import io.github.lucklike.luckyclient.api.util.DelayedOutput;

import java.util.Map;

public class SparkCompletionsEventListener implements EventListener {

    @Override
    public void onOpen(Event<Response> event) throws Exception {
        Response response = event.getMessage();
        int status = response.getStatus();
        if (status != 200) {
            throw new LuckyRuntimeException("讯飞火星API调用失败，异常的HTTP状态码[{}]: {}", status, response.getStringResult());
        }
    }

    @Override
    public void onMessage(Event<Message> event) throws Exception {
        int l = 70;
        Message message = event.getMessage();
        if (message.hasData()) {
            if (!" [DONE]".equals(message.getData())) {
                ConfigurationMap configMap = message.jsonDataToMap();
                int code = configMap.getInt("code");
                if (code == 0) {
                    String output = configMap.getString("choices[0].delta.content");
                    DelayedOutput.output(output);
                } else if (code == 11200) {
                    Console.printRed("授权错误：该appId没有相关功能的授权 或者 业务量超过限制!");
                } else if (code == 10007) {
                    Console.printRed("用户流量受限：服务正在处理用户当前的问题，需等待处理完成后再发送新的请求。（必须要等大模型完全回复之后，才能发送下一个问题）!");
                } else if (code == 10013) {
                    Console.printRed("输入内容审核不通过，涉嫌违规，请重新调整输入内容");
                } else if (code == 10014) {
                    Console.printRed("输出内容涉及敏感信息，审核不通过，后续结果无法展示给用户");
                } else if (code == 10019) {
                    Console.printRed("本次会话内容有涉及违规信息的倾向；建议开发者收到此错误码后给用户一个输入涉及违规的提示");
                } else if (code == 10907) {
                    Console.printRed("token数量超过上限。对话历史+问题的字数太多，需要精简输入");
                } else if (code == 11201) {
                    Console.printRed("授权错误：日流控超限。超过当日最大访问量的限制");
                } else if (code == 11202) {
                    Console.printRed("授权错误：秒级流控超限。秒级并发超过授权路数限制");
                } else if (code == 11203) {
                    Console.printRed("授权错误：并发流控超限。并发路数超过授权路数限制");
                } else {
                    Console.printRed("未知错误码：" + code);
                }


            } else {
                DelayedOutput.clearOutputLength();
                System.out.println("\n");
            }
        }
    }

}
