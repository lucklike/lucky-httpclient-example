package io.github.lucklike.luckyclient.api.plugin;

import com.luckyframework.httpclient.generalapi.plugin.TimeStatistics;
import io.github.lucklike.httpclient.discovery.HttpClient;
import lombok.Data;

/**
 * @author fukang
 * @version 1.0.0
 * @date 2025/2/16 12:53
 */
@Data
@HttpClient
@TimeStatistics(slow = 2)
public abstract class FieldApi {
    private Integer id;
    private String name;
    private boolean ok;
    private Boolean end;
}
