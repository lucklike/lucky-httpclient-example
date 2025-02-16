package io.github.lucklike.luckyclient.api.cairh.ect888;

import com.luckyframework.httpclient.proxy.annotations.Post;
import io.github.lucklike.luckyclient.api.cairh.ect888.ann.Ect888Client;

@Ect888Client
public interface Ect888Api {

    @Post
    String call(Object request);
}
