package io.github.lucklike.luckyclient.api.mock.type;

import lombok.Data;

/**
 * @author fukang
 * @version 1.0.0
 * @date 2025/11/4 01:30
 */
@Data
public class BaseResult<T> {
    private Integer code;
    private String message;
    private T data;
}
