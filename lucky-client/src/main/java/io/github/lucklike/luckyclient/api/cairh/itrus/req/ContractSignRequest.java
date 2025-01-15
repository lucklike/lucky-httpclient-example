package io.github.lucklike.luckyclient.api.cairh.itrus.req;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Map;

/**
 * 天威合同创建用户入参
 *
 * @author zxk
 * @since 2022-11-11
 */
@Data
@Accessors(chain = true)
public class ContractSignRequest {

    /**
     * contractId
     */
    private String contractId;

    /**
     * signer
     */
    private Signer signer;

    @Data
    public static class Signer {

        /**
         * signerId
         */
        private Long signerId;

        /**
         * signFiles
         */
        private List<SignFile> signFiles;
    }

    @Data
    public static class SignFile {

        /**
         * docId
         */
        private Long docId;

        private List<ControlValue> controlValues;

        /**
         * signControl
         */
        Map<String, String> signControl;
    }

    @Data
    public static class ControlValue {

        private Integer controlsId;
        private Long sealId;
        private Long stampId;
    }
}