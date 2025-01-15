package io.github.lucklike.luckyclient.api.cairh.itrus.req;

import lombok.Data;

@Data
public class QuerySealRequest {

    /**
     * ⽤户id
     */
    private String userId;


    /**
     * 企业id
     */
    private String enterpriseId;


    /**
     * 印章id
     */
    private String id;


    /**
     * 印章名称
     */
    private String name;


    /**
     * 是否需要返回图⽚Base64
     * 默认：false
     *
     */
    private String requiredBase64;


    /**
     * 是否需要返回授权⼈
     * 默认：false
     */
    private String requiredAuthorizer;


    /**
     * ⻚码
     * 默认：1
     */
    private String pageNum;


    /**
     * 条数
     * 默认：10 最⼤：50
     */
    private String pageSize;
}
