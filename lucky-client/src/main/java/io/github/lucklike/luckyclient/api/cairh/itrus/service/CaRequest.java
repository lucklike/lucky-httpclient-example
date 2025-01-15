package io.github.lucklike.luckyclient.api.cairh.itrus.service;

import lombok.Data;

import java.io.Serializable;

@Data
public class CaRequest implements Serializable {

    private static final long serialVersionUID = 777481460040649856L;

    /**
     * 协议签署编号
     */
    private String agreementsign_id;

    /**
     * 客户姓名
     */
    private String user_name;

    /**
     * 证件号码
     */
    private String id_no;

    /**
     * 证件类型
     */
    private String id_kind;

    /**
     * 电话
     */
    private String mobile_tel;

    /**
     * 企业名称
     */
    private String csdc_ent_name;

    /**
     * 企业机构证件号
     */
    private String csdc_busi_id_no;

    /**
     * 企业印章图像
     */
    private String seal_image_base64;

    /**
     * 手写印章编号
     */
    private String ca_seal_id;

    /**
     * 用户userId
     */
    private String ca_user_id;

    /**
     * 文件流
     */
    private byte[] file;

    /**
     * 签名区域配置
     */
    private String sign_pos;

    /**
     * 签章区域配置
     */
    private String seal_pos;

    /**
     * 协议名称
     */
    private String agreement_name;

    /**
     * 第三方协议ID
     */
    private String third_agreement_id;

    /**
     * 自定义替换通配符字段 如果有套打字段，此字段需要传json格式业务数据集合，如：{"curr_date":"20190508","op_branch_no_name":"test1"}
     */
    private String replace_str;
}
