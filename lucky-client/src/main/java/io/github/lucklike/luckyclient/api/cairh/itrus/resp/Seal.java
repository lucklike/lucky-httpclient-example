package io.github.lucklike.luckyclient.api.cairh.itrus.resp;

import lombok.Data;

import java.util.List;

@Data
public class Seal {


    /**
     * 企业id
     */
    private String enterpriseId;

    /**
     * 印章id
     */
    private Long id;

    /**
     * 创建⼈⽤户id
     */
    private String creator;

    /**
     * 印章名称
     */
    private String name;

    /**
     * 是否默认
     */
    private String defaulted;

    /**
     * 形状
     */
    private String shape;

    /**
     * 宽度
     */
    private String width;

    /**
     * ⾼度
     */
    private String height;

    /**
     * 印章类型
     */
    private String sealForm;

    /**
     * 印章状态
     */
    private String sealStatus;

    /**
     * 创建时间
     * 格式：yyyy-MM-dd HH:mm:ss
     */
    private String createTime;

    /**
     * 是否有效
     */
    private String valid;

    /**
     * 是否可⽤
     */
    private String useful;


    /**
     * 印章图⽚Base64
     */
    private String sealFile;

    /**
     * 图⽚类型 0:png 1:svg
     */
    private String picType;

    /**
     * 被授权⼈
     */
    private List<Authorizer> authorizer;

    @Data
    public static class Authorizer {

        /**
         * ⽤户id
         */
        private String userId;

        /**
         * 姓名
         */
        private String displayName;
    }
}
