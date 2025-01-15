package io.github.lucklike.luckyclient.api.cairh.itrus.resp;

import lombok.Data;

@Data
public class CreateEnterpriseSealResponse {

    /**
     * 印章id
     * operationMode=0时返回
     */
    private Long sealId;

    /**
     * 印章名称
     * operationMode=0时返回
     */
    private String sealName;

    /**
     * 图⽚base64
     * operationMode=1时返回
     */
    private String base64;
}
