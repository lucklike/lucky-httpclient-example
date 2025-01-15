package io.github.lucklike.luckyclient.api.cairh.itrus.req;

import lombok.Data;

@Data
public class AddSubCompanyRequest {

    /**
     * 邀请企业id
     */
    private String enterpriseId;

    /**
     * 受邀企业id
     */
    private String subCompanyUuid;
}
