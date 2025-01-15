package io.github.lucklike.luckyclient.api.cairh.itrus.resp;

import lombok.Data;

@Data
public class EnterpriseCreateResponse {

    /**
     * 企业标识
     */
    private String enterpriseId;

    /**
     * 证书Id
     */
    private Long certId;
}
