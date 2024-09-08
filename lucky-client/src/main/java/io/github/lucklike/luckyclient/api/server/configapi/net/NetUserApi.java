package io.github.lucklike.luckyclient.api.server.configapi.net;

import com.luckyframework.httpclient.proxy.annotations.Condition;
import com.luckyframework.httpclient.proxy.annotations.RespConvert;
import io.github.lucklike.entity.request.User;
import io.github.lucklike.entity.response.Result;

@NetHttpClient
@RespConvert("#{$body$.data}")
@Condition(assertion = "#{$status$ != 200}", exception = "接口相应异常,响应码：【#{$status$}】 [#{$reqMethod$}] #{$url$} ")
@Condition(assertion = "#{$body$.code != 200}", exception = "接口相应异常! core:#{$body$.code}. message: #{$body$.message}")
public interface NetUserApi {

    User postUser(User user);

    Result<User> getUser();
}
