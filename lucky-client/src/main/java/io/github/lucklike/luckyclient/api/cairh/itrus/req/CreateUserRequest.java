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
public class CreateUserRequest {

    /**
     * type
     */
    private String type;

    /**
     * 电话号码
     */
    private String phone;

    /**
     * 授权
     */
    private String authentication;

    /**
     * 身份证类型
     */
    private String idCardType;

    /**
     * 身份证号码
     */
    private String idCardNum;

    /**
     * 身份证姓名
     */
    private String displayName;

}