package io.github.lucklike.luckyclient.api.validator;

import com.luckyframework.httpclient.generalapi.plugin.Validated;
import com.luckyframework.httpclient.proxy.annotations.Get;
import com.luckyframework.httpclient.proxy.annotations.HttpExec;
import com.luckyframework.httpclient.proxy.annotations.JsonBody;
import com.luckyframework.httpclient.proxy.mock.Mock;
import io.github.lucklike.httpclient.discovery.HttpClient;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 * @author fukang
 * @version 1.0.0
 * @date 2025/10/24 00:52
 */
@HttpExec.jdk
@Validated
@HttpClient("http://localhost:8080/mock")
public interface ValidatorApi {

//    @Mock(body = "Mock Response")
    @NotBlank
    @Get("validator")
    String validator(@JsonBody @Valid Entity entity);
}

