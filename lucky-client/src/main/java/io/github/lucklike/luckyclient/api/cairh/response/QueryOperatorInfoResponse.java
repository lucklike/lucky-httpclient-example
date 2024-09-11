package io.github.lucklike.luckyclient.api.cairh.response;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 查询操作员响应
 *
 * @author hf
 */
@Data
public class QueryOperatorInfoResponse {

    //员工号
    private String staffNo;

    //用户姓名
    private String userName;

    //营业部编号
    private String branchNo;

    //手机号码
    private String mobileTel;

    //电子邮箱
    private String eMail;

    //地址
    private String address;

    //性别
    private String gender;

    //从业证书编号
    private String professionCert;

    //从业证书失效日期
    private Integer professionCertEndDate;

    //头像文件Id
    private String imageFileId;

    //头像base64
    private String base64Image;

    //固定电话
    private String phoneTel;

    //账号失效日期
    private Integer expireEndDate;

    //券商名称
    private String securityName;

    //券商电话
    private String securityServicePhone;
}