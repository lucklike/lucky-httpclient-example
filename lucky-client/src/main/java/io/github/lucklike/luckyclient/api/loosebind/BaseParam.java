package io.github.lucklike.luckyclient.api.loosebind;

import lombok.Data;

@Data
public class BaseParam {

    /**
     * URL
     */
    private String url;

    /**
     * 菜单ID
     */
    private String menu_id;

    /**
     * 操作员营业部号
     */
    private String op_branch_no;

    /**
     * 操作员号
     */
    private String operator_no;

    /**
     * 操作员密码
     */
    private String op_password;

    /**
     * 用户营业部号
     */
    private String branch_no;

    /**
     * 用户站点地址
     */
    private String op_station;

    /**
     * 用户委托方式
     */
    private String op_entrust_way;

    /**
     * 固定1
     */
    private String audit_action;

    /**
     * 密码类型
     */
    private String password_type;

    /**
     * 密码
     */
    private String password;

    /**
     * 操作备注
     */
    private String op_remark;
}
