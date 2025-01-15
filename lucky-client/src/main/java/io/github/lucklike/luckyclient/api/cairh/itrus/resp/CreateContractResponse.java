package io.github.lucklike.luckyclient.api.cairh.itrus.resp;

import lombok.Data;

@Data
public class CreateContractResponse {

    /**
     * 合同id
     */
    private String contractId;

    /**
     * 合同编号
     */
    private String code;

    /**
     * 合同名称
     */
    private String contractName;

    /**
     * 合同状态:0草稿、1.待签署，2.签署中，3.已完成，4.已过期，5.已撤回，6.已拒签，11.已解约
     */
    private String status;

    /**
     * 合同文档id
     */
    private String docId;

    /**
     * 合同文档名称
     */
    private String docName;
}
