package io.github.lucklike.luckyclient.api.plugin;

import io.github.lucklike.httpclient.discovery.HttpClient;
import lombok.Data;

/**
 * @author fukang
 * @version 1.0.0
 * @date 2025/2/16 12:53
 */
@Data
@HttpClient
public abstract class FieldApi {
    private Integer id;
    private String name;
    private boolean ok;
    private Boolean end;
}
