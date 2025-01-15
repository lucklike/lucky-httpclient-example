package io.github.lucklike.luckyclient.api.cairh.itrus.resp;

import lombok.Data;

import java.util.List;

@Data
public class QueryEnterpriseResponse {

    /**
     * 总记录数
     */
    private String total;

    /**
     * 企业信息列表
     */
    private List<Org> companyList;

    @Data
    public static class Org {

        /**
         * 企业名称
         */
        private String orgName;

        /**
         * 企业简称
         */
        private String orgAlias;

        /**
         * 企业类型
         */
        private String orgType;

        /**
         * 企业id
         */
        private String companyUuid;

        /**
         * 企业状态
         * 0=未激活 1=正常 2=挂起
         */
        private String status;

        /**
         * 主管理员⽤户id
         */
        private String creatorUuid;

        /**
         * 创建时间
         * 格式：yyyy-MM-dd HH:mm:ss
         */
        private String createTime;

        /**
         * 企业⻆⾊
         * 1=平台客户 2=销售客户 3=渠道商 4=渠道客户
         */
        private String companyRole;

        /**
         * 第三⽅企业唯⼀标识
         */
        private String thirdUniqueId;

        /**
         * 实名类型
         */
        private String authType;

        /**
         * 实名结果
         */
        private String authResult;

        /**
         * 企业实名信息
         */
        private AuthInfo authInfo;

        /**
         * 管理员账号信息
         */
        private Manager managerInfo;
    }

    @Data
    public static class AuthInfo {

        /**
         * 实名结果
         */
        private String authResult;

        /**
         * 实名类型
         */
        private String authType;

        /**
         * 企业类型
         */
        private String orgType;

        /**
         * ⼯商注册号
         */
        private String registerNumber;

        /**
         * 组织机构代码
         */
        private String unitCode;

        /**
         * 统⼀社会信⽤代码
         */
        private String socialCode;

        /**
         * 是否三证合⼀
         */
        private String inOneType;

        /**
         * 提交时间
         * 格式：yyyy-MM-dd HH:mm:ss
         */
        private String authApplyTime;

        /**
         * 认证时间
         * 格式：yyyy-MM-dd HH:mm:ss
         */
        private String authFinishTime;

        /**
         * 银⾏账号
         */
        private String bankCardNum;

        /**
         * 开户银⾏名称
         */
        private String bankName;

        /**
         * 开户⽀⾏名称
         */
        private String subBankName;

        /**
         * 银⾏编码
         */
        private String bankCode;

        /**
         * 法定代表⼈证件类型
         */
        private String legalCardType;

        /**
         * 法定代表⼈证件号码
         */
        private String legalIdCard;

        /**
         * 法定代表⼈名称
         */
        private String legalName;

        /**
         * 法定代表⼈⼿机号
         */
        private String legalPhone;

        /**
         * 法定代表⼈账号uuid
         */
        private String legalUuid;

        /**
         * 企业状态
         */
        private String orgState;

        /**
         * 实名id
         */
        private String authId;

        /**
         * 认证⽅
         */
        private String certifier;

        /**
         * 其他证件号码
         */
        private String otherCardName;

        /**
         * 企业证件类型
         */
        private String companyCardType;
    }

    @Data
    private static class Manager {

        /**
         * 姓名
         */
        private String creatorName;

        /**
         * 邮箱
         */
        private String email;

        /**
         * ⼿机号
         */
        private String phone;
    }

}
