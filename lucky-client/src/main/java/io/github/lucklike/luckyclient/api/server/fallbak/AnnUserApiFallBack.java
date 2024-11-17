package io.github.lucklike.luckyclient.api.server.fallbak;

import io.github.lucklike.entity.request.User;
import io.github.lucklike.entity.response.Result;
import io.github.lucklike.luckyclient.api.server.ann.AnnUserApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AnnUserApiFallBack implements AnnUserApi {

    @Override
    public User postUser(User user) {
        User user1 = new User();
        user1.setId(3333);
        user1.setEmail("FallBack@example.com");
        user1.setPhone("123544454");
        return user1;
    }

    @Override
    public Result<User> getUser() {
        User user = new User();
        user.setId(3333);
        user.setEmail("FallBack@example.com");
        user.setPhone("123544454");
        return Result.success(user);
    }

    @Override
    public void error() {

    }

    //【#{#hasText($api.name) ? $api.name : ''}】#{#nonText($api.id) ? '' : '(' + (#hasText($api.version) ? #str('{}/{}', $api.id, $api.version) : $api.id) + ')'} 接口响应码异常：'#{#hasText(_codeName_) ? _codeName_ + ' = ' : ''}#{__code__}' #{#hasText(_codeErrMsgName_) ? _codeErrMsgName_ + ' = ' : ''}#{#hasText(_msg_} ? _msg_ + ',' : ''#{#nonText($api.author) ? '' : '请联系接口维护人员：' + (#hasText($api.contactWay) ? #str('{}/{}', $api.author, $api.contactWay) : $api.author) + ', '}接口地址：[#{$reqMethod$}]#{$url$}

}
