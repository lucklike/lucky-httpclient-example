package io.github.lucklike.luckyclient.api.cairh.itrus.req;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 天威合同创建用户入参
 *
 * @author zxk
 * @since 2022-11-11
 */
@Data
@Accessors(chain = true)
public class CreateAutographRequest {

    /**
     * userId
     */
    private String userId;

    /**
     * autographType
     */
    private String autographType;

    /**
     * autographName
     */
    private String autographName;
}