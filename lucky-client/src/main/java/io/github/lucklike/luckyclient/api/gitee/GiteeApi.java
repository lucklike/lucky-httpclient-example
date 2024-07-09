package io.github.lucklike.luckyclient.api.gitee;

import io.github.lucklike.entity.request.gitee.Stargazers;
import io.github.lucklike.httpclient.configapi.LocalConfigHttpClient;

import java.util.List;

/**
 * GiteeApi
 *
 * @author fukang
 * @version 1.0.0
 * @date 2024/7/9 01:28
 */
@LocalConfigHttpClient
public interface GiteeApi {

    List<Stargazers>  stargazers(String owner, String repo);
}
