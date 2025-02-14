package io.github.lucklike.luckyclient.api.ollama;

import com.luckyframework.httpclient.generalapi.describe.Describe;
import com.luckyframework.httpclient.proxy.annotations.Delete;
import com.luckyframework.httpclient.proxy.annotations.Get;
import com.luckyframework.httpclient.proxy.annotations.JsonBody;
import com.luckyframework.httpclient.proxy.annotations.Post;
import com.luckyframework.httpclient.proxy.sse.EventListener;
import com.luckyframework.httpclient.proxy.sse.Sse;
import io.github.lucklike.luckyclient.api.ollama.req.ChatRequest;
import io.github.lucklike.luckyclient.api.ollama.req.CopyRequest;
import io.github.lucklike.luckyclient.api.ollama.req.GenerateRequest;
import io.github.lucklike.luckyclient.api.ollama.req.ModelPullRequest;
import io.github.lucklike.luckyclient.api.ollama.req.ModelRequest;
import io.github.lucklike.luckyclient.api.ollama.resp.ChatResponse;
import io.github.lucklike.luckyclient.api.ollama.resp.GenerateResponse;
import io.github.lucklike.luckyclient.api.ollama.resp.ModelDetails;
import io.github.lucklike.luckyclient.api.ollama.resp.ModelList;

/**
 * Ollama REST API
 */
@OllamaClient
public interface OllamaApi {

    //----------------------------------------------------------------------
    //                          回答补全(api/generate)
    //----------------------------------------------------------------------

    @Sse
    @Post("/api/generate")
    @Describe("（流式）回答补全")
    void streamGenerate(@JsonBody GenerateRequest request, EventListener listener);

    @Post("/api/generate")
    @Describe("（非流式）回答补全")
    GenerateResponse generate(@JsonBody GenerateRequest request);


    //----------------------------------------------------------------------
    //                          对话补全(/api/chat)
    //----------------------------------------------------------------------


    @Sse
    @Post("/api/chat")
    @Describe("（流式）对话补全")
    void streamChat(@JsonBody ChatRequest request, EventListener listener);

    @Post("/api/chat")
    @Describe("（非流式）对话补全")
    ChatResponse chat(@JsonBody ChatRequest request);

    //----------------------------------------------------------------------
    //                          列出本地模型(/api/tags)
    //----------------------------------------------------------------------

    @Get("/api/tags")
    @Describe("列出本地模型")
    ModelList localModels();

    //----------------------------------------------------------------------
    //                          列出运行模型(/api/ps)
    //----------------------------------------------------------------------

    @Get("/api/ps")
    @Describe("列出运行模型")
    ModelList psModels();

    //----------------------------------------------------------------------
    //                          显示模型信息(/api/show)
    //----------------------------------------------------------------------

    @Post("/api/show")
    @Describe("显示模型信息")
    ModelDetails showModel(@JsonBody ModelRequest request);

    //----------------------------------------------------------------------
    //                          拉取模型(/api/pull)
    //----------------------------------------------------------------------

    @Sse
    @Post("/api/pull")
    @Describe("（流式返回）拉取模型")
    void streamPullModel(@JsonBody ModelPullRequest request, EventListener listener);


    //----------------------------------------------------------------------
    //                          删除模型(/api/delete)
    //----------------------------------------------------------------------

    @Delete("/api/delete")
    @Describe("删除模型")
    void deleteModel(@JsonBody ModelRequest request);


    //----------------------------------------------------------------------
    //                          复制模型(/api/copy)
    //----------------------------------------------------------------------

    @Post("/api/copy")
    @Describe("复制模型")
    void copyModel(@JsonBody CopyRequest request);
}
