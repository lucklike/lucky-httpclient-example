package io.github.lucklike.server.controller;

import io.github.lucklike.server.ai.baidu.BaiduAI;
import io.github.lucklike.server.ai.baidu.BaiduAISseEventListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Controller
@RequestMapping("/sse")
public class SseController {

    @Autowired
    private BaiduAI baiduAI;

    @GetMapping("/events")
    public SseEmitter streamEvents(String content) {
        SseEmitter sseEmitter = new SseEmitter(-1L);
        baiduAI.questionsAndAnswers(content, new BaiduAISseEventListener(sseEmitter));
        return sseEmitter;
    }
}
