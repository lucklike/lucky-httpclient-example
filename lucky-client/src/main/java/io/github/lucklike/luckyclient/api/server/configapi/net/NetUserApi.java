package io.github.lucklike.luckyclient.api.server.configapi.net;

import com.luckyframework.httpclient.proxy.annotations.Branch;
import com.luckyframework.httpclient.proxy.annotations.RespConvert;
import io.github.lucklike.entity.request.User;
import io.github.lucklike.entity.response.Result;

@RespConvert(
    conditions = {
        @Branch(assertion = "#{$status$ != 200}", exception = "接口相应异常,响应码：【#{$status$}】 [#{$reqMethod$}] #{$url$} "),
        @Branch(assertion = "#{$body$.code != 200}", exception = "接口相应异常! core:#{$body$.code}. message: #{$body$.message}")
    },
    result = "#{$body$.data}"
)
@NetHttpClient
public interface NetUserApi {

    User postUser(User user);

    Result<User> getUser();
}
