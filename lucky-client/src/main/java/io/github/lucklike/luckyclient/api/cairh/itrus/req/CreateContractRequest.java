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
public class CreateContractRequest {

    /**
     * signCount
     */
    private String signCount;

    /**
     * name
     */
    private String name;

    /**
     * docName
     */
    private String docName;

    /**
     * base64
     */
    private String base64;

    /**
     * creator
     */
    private String creator;

    /**
     * enterpriseId
     */
    private String enterpriseId;

    /**
     * waterMarkOff
     */
    private String waterMarkOff;
}