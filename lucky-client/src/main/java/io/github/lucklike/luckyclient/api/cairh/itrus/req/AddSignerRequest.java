package io.github.lucklike.luckyclient.api.cairh.itrus.req;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 天威合同创建用户入参
 *
 * @author zxk
 * @since 2022-11-11
 */
@Data
@Accessors(chain = true)
public class AddSignerRequest {

    /**
     * contractId
     */
    private String contractId;

    /**
     * name
     */
    private List<Signer> signers;

    @Data
    public static class Signer {

        /**
         * signerType
         */
        private String signerType;

        /**
         * userId
         */
        private String userId;

        /**
         * 企业ID
         */
        private String enterpriseId;

        /**
         * sequence
         */
        private String sequence;

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
        private String docId;

        /**
         * xySignControls
         */
        private List<XySignControl> xySignControls;

        /**
         * keywordSignControls
         */
        private List<KeywordSignControl> keywordSignControls;
    }

    @Data
    public static class XySignControl {
        /**
         * id
         */
        private Integer id;

        /**
         * type
         */
        private String type;

        /**
         * pageNum
         */
        private String pageNum;

        /**
         * 取值范围：0-29.7
         * 默认：图⽚宽度
         * 印章、图⽚控件,可不填
         */
        private Float width;

        /**
         * 取值范围：0-29.7
         * 默认：图⽚宽度
         * 印章、图⽚控件,可不填
         */
        private Float height;

        /**
         * x
         */
        private String x;

        /**
         * y
         */
        private String y;
    }

    @Data
    public static class KeywordSignControl {
        /**
         * id
         */
        private Integer id;

        /**
         * type
         */
        private String type;

        /**
         * pageNum
         */
        private String pageNum;

        /**
         * keyword
         */
        private String keyword;

        /**
         * 取值范围：0-29.7
         * 默认：图⽚宽度
         * 印章、图⽚控件,可不填
         */
        private Float width;

        /**
         * 取值范围：0-29.7
         * 默认：图⽚宽度
         * 印章、图⽚控件,可不填
         */
        private Float height;

        /**
         * offsetX
         */
        private String offsetX;

        /**
         * offsetY
         */
        private String offsetY;
    }

}