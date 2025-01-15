package io.github.lucklike.luckyclient.api.cairh.itrus.req;

import lombok.Data;

@Data
public class QueryEnterpriseRequest {

    /**
     * 企业id
     */
    private String enterpriseId;


    /**
     * 企业全称
     */
    private String acompanyName;


    /**
     * 社会统⼀信⽤代码
     */
    private String socialCode;


    /**
     * 创建开始时间
     * 格式：yyyy-MM-dd
     */
    private String startTime;


    /**
     * 创建结束时间
     */
    private String endTime;


    /**
     * ⻚码
     */
    private String pageNum;


    /**
     * 条数
     */
    private String pageSize;
}
