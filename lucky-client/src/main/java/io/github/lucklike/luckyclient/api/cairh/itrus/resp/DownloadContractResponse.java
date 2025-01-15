package io.github.lucklike.luckyclient.api.cairh.itrus.resp;

import lombok.Data;

@Data
public class DownloadContractResponse {

    /**
     * 合同⽂件的字节数组
     */
    private byte[] data;

    /**
     * ⽂件名称
     */
    private String fileName;

    /**
     * ⽂件后缀（.pdf/.zip）
     */
    private String type;
}
