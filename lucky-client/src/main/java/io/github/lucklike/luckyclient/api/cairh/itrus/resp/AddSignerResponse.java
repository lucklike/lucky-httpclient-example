package io.github.lucklike.luckyclient.api.cairh.itrus.resp;

import lombok.Data;

import java.util.List;

@Data
public class AddSignerResponse {

    /**
     * 合同编号
     */
    private String code;

    /**
     * 合同id
     */
    private String contractId;

    /**
     * 合同主题
     */
    private String contractName;

    /**
     * 合同状态:0草稿、1.待签署，2.签署中，3.已完成，4.已过期，5.已撤回，6.已拒签，11.已解约
     */
    private Integer status;

    /**
     * 合同签署人
     */
    private List<Signer> signers;

    /**
     * 最后签署时间
     */
    private String lastSignTime;

    /**
     * 当前签署人ID
     */
    private List<Long> currSignerIds;

    @Data
    public static class Signer {

        /**
         * 签署⼈类型1=个⼈ 2=企业
         */
        private Integer signerType;

        /**
         * 签署人ID
         */
        private Long signerId;

        /**
         * 签署人用户id
         */
        private String userId;

        /**
         * 合同签署人姓名
         */
        private String userName;

        /**
         * 合同签署人签署状态：1.待他人签署，2 待我签（.签署中），3.已签署，5.已解约，6.已拒签
         */
        private Integer signStatus;

        /**
         * 合同文档
         */
        private List<Document> docList;
    }

    @Data
    public static class Document {

        /**
         * 合同文件Id
         */
        private Long docId;

        /**
         * 合同文件名称
         */
        private String docName;

        /**
         * 合同文件签署状态
         */
        private Integer docStatus;
    }
}
