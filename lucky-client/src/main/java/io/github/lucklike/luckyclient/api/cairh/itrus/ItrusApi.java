package io.github.lucklike.luckyclient.api.cairh.itrus;

import com.luckyframework.httpclient.generalapi.describe.Describe;
import com.luckyframework.httpclient.proxy.annotations.JsonBody;
import com.luckyframework.httpclient.proxy.annotations.Post;
import io.github.lucklike.luckyclient.api.cairh.itrus.req.ItrusContractCreateUserRequest;
import io.github.lucklike.luckyclient.api.cairh.itrus.resp.CreateUserResponse;

import java.util.Optional;

@ItrusHttpClient
public interface ItrusApi {

    @Describe("天威合同创建用户")
    @Post("/user/create")
    Optional<CreateUserResponse> createUser(@JsonBody ItrusContractCreateUserRequest request);

}
