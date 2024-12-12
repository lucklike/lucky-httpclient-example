package io.github.lucklike.luckyclient.api.cairh.basedata.resp;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

@Data
@Accessors(chain = true)
public class OperInfoQry {

    /**
     * 用户编号
     */
    private String staff_no;

    /**
     * 用户所属营业部编号
     */
    private String branch_no;

    /**
     * 用户所属营业部编号名
     */
    private String branch_no_name;

    /**
     * 用户姓名
     */
    private String user_name;

    /**
     * 手机号码
     */
    private String mobile_tel;

    /**
     * 邮箱
     */
    private String e_mail;

    /**
     * 联系地址
     */
    private String address;

    /**
     * 可操作营业部
     */
    private String en_branch_nos;

    /**
     * 性别
     */
    private String gender;

    /**
     * 职业证书编号
     */
    private String profession_cert;

    /**
     * 职业证书有效期截止时间
     */
    private Integer profession_cert_enddate;

    /**
     * 角色权限
     */
    private String en_roles;


    private String create_by;

    /**
     * 该账号的创建人系列USER_ID，通过路径追溯与此账号相关的所有人
     */
    private String user_id_path;

    /**
     * 操作员头像
     */
    private String base64_image;

    /**
     * 头像文件名
     */
    private String image_file_name;

    /**
     * 固定电话
     */
    private String phone_tel;

    /**
     * 此账号的有效期截止时间
     */
    private Integer expire_enddate;

    /**
     * 密码
     */
    private String password;

    /**
     * 密钥
     */
    private String secret_key;

    /**
     * 绑定mac
     */
    private String bind_mac;

    /**
     * 状态
     */
    private String status;

    /**
     * 绑定ip
     */
    private String bind_ip;

    /**
     * 登录错误次数
     */
    private Integer login_error_times;

    /**
     * 锁定状态
     */
    private boolean locked;

    private String locked_tranfer;

    /**
     * 用户类型
     */
    private String user_type;

    /**
     * 创建时间
     */
    private Long create_datetime;

    /**
     * 员工组
     */
    private String operator_group;

    /**
     * 弹性扩展字段
     */
    private List<AttributeKeyValueEx> exs;
}
