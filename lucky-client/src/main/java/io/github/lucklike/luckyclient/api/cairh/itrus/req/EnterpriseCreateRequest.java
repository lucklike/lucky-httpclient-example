package io.github.lucklike.luckyclient.api.cairh.itrus.req;

import lombok.Data;


@Data
public class EnterpriseCreateRequest {

    /**
     * ⽤户id Y
     */
    private String userId;

    /**
     * 企业全称 Y
     */
    private String orgName;

    /**
     * 证件类型 N 默认：N
     */
    private String idType;

    /**
     * 证件号,N  authentication=true时必填
     */
    private String orgCode;

    /**
     * 其他证件类型名称
     * idType="Z"时必填
     */
    private String otherCardName;

    /**
     * 企业类型 N
     */
    private String enterpriseType;

    /**
     * 实名认证来源 Y
     * true=客户认证
     * false=天威认证
     */
    private Boolean authentication;

    /**
     * 企业标签
     * 需在诚信签前置web⻚⾯配置，不传使⽤默认标签
     */
    private String enterpriseLabel;

    /**
     * 法定代表⼈姓名
     */
    private String legalName;

    /**
     * 法定代表⼈⼿机号
     */
    private String legalPhone;

    /**
     * 法定代表⼈证件号
     */
    private String legalIdCard;

    /**
     * 法定代表⼈证件类型
     */
    private String legalIdCardTyp;

    /**
     * 企业三⽅id
     */
    private String thirdUniqueId;


}
