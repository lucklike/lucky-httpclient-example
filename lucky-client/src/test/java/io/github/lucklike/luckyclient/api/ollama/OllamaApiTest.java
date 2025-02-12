package io.github.lucklike.luckyclient.api.ollama;

import com.luckyframework.common.Color;
import com.luckyframework.common.Console;
import com.luckyframework.httpclient.generalapi.DelayedOutput;
import io.github.lucklike.luckyclient.api.ollama.listener.OllamaChatEventListener;
import io.github.lucklike.luckyclient.api.ollama.listener.OllamaGenerateEventListener;
import io.github.lucklike.luckyclient.api.ollama.listener.OllamaModelPullEventListener;
import io.github.lucklike.luckyclient.api.ollama.req.ChatRequest;
import io.github.lucklike.luckyclient.api.ollama.req.GenerateRequest;
import io.github.lucklike.luckyclient.api.ollama.req.Message;
import io.github.lucklike.luckyclient.api.ollama.req.ModelPullRequest;
import io.github.lucklike.luckyclient.api.ollama.req.ModelRequest;
import io.github.lucklike.luckyclient.api.ollama.resp.ChatResponse;
import io.github.lucklike.luckyclient.api.ollama.resp.GenerateResponse;
import io.github.lucklike.luckyclient.api.ollama.resp.Model;
import io.github.lucklike.luckyclient.api.ollama.resp.ModelDetails;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Scanner;

@SpringBootTest
class OllamaApiTest {

    @Resource
    private OllamaApi api;

    @Test
    void streamGenerate() {
        OllamaGenerateEventListener listener = new OllamaGenerateEventListener();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            Console.printlnGreen("ME: ");
            String prompt = scanner.nextLine();


            GenerateRequest request = new GenerateRequest();
            request.setPrompt(prompt);
            request.setContext(listener.getContext());

            Console.printlnMulberry("\nAI:");
            api.streamGenerate(request, listener);
        }
    }

    @Test
    void generate() {
        Scanner scanner = new Scanner(System.in);
        GenerateResponse lastResp = null;
        while (true) {
            Console.printlnGreen("ME: ");
            String prompt = scanner.nextLine();


            GenerateRequest request = new GenerateRequest();
            request.setPrompt(prompt);
            request.setContext(lastResp == null ? null : lastResp.getContext());

            Console.printlnMulberry("\nAI:");
            lastResp = api.generate(request);

            DelayedOutput.output(lastResp.getResponse() + "\n", Color.CYAN, 80, 10);

        }
    }


    @Test
    void streamChat() {
        OllamaChatEventListener listener = new OllamaChatEventListener();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            Console.printlnGreen("ME: ");
            String content = scanner.nextLine();

            ChatRequest request = new ChatRequest();

            Message msg = new Message();
            msg.setContent(content);
            msg.setRole("user");

            request.setMessages(new Message[] { msg });

            Console.printlnMulberry("\nAI:");
            api.streamChat(request, listener);
        }
    }

    @Test
    void chat() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            Console.printlnGreen("ME: ");
            String content = scanner.nextLine();

            ChatRequest request = new ChatRequest();
            Message msg = new Message();
            msg.setContent(content);
            msg.setRole("user");

            request.setMessages(new Message[] { msg });

            Console.printlnMulberry("\nAI:");
            ChatResponse resp = api.chat(request);

            DelayedOutput.output(resp.getMessage().getContent() + "\n", Color.CYAN, 80, 10);

        }
    }

    @Test
    void localModels() {
        Model[] models = api.localModels().getModels();
        for (Model model : models) {
            System.out.println(model);
        }
    }

    @Test
    void psModels() {
        Model[] models = api.psModels().getModels();
        for (Model model : models) {
            System.out.println(model);
        }
    }


    @Test
    void showModel() {
        ModelRequest request = new ModelRequest();
        request.setName("deepseek-r1:7b");
        request.setVerbose(true);
        ModelDetails modelDetails = api.showModel(request);
        System.out.println(modelDetails);
    }


    @Test
    void streamPullModel() {
        ModelPullRequest request = new ModelPullRequest();
        request.setName("deepseek-r1:8b");
        api.streamPullModel(request, new OllamaModelPullEventListener());
    }


    @Test
    void deleteModel() {
        ModelRequest request = new ModelRequest();
        request.setName("deepseek-r1:8b");
        String s = api.deleteModel(request);
        System.out.println(s);
    }
}