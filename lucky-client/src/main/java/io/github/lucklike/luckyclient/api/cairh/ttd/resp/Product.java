package io.github.lucklike.luckyclient.api.cairh.ttd.resp;

import com.luckyframework.common.ConfigurationMap;
import lombok.Data;

import java.util.List;

import static com.luckyframework.httpclient.proxy.function.SerializationFunctions._json;


/**
 * @author fukang
 * @version 1.0.0
 * @date 2025/2/16 02:44
 */
@Data
public class Product {
    private String riskLevel;
    private Integer saleType;
    private String watermarkContractSignUrl;
    private ConfigurationMap watermarkContractSignUrl$Map;
    private String productName;
    private String manageCode;
    private String contractUrl;
    private ConfigurationMap contractUrl$Map;
    private String trustUserName;
    private String productNo;
    private String productSerialNo;
    private Integer bookStatus;
    private String errorDetailMsg;
    private Integer stockBookStatus;
    private List<Contract> suppleContractList;
    private String revealBookUrl;
    private ConfigurationMap revealBookUrl$Map;
    private String contractFlowNo;
    private String errorMsg;
    private String manageName;
    private String category;

    public void setContractUrl(String contractUrl) throws Exception {
        this.contractUrl = contractUrl;
        this.contractUrl$Map = _json(contractUrl, ConfigurationMap.class);
    }

    public void setRevealBookUrl(String revealBookUrl) throws Exception {
        this.revealBookUrl = revealBookUrl;
        this.revealBookUrl$Map = _json(revealBookUrl, ConfigurationMap.class);
    }

    public void setWatermarkContractSignUrl(String watermarkContractSignUrl) throws Exception {
        this.watermarkContractSignUrl = watermarkContractSignUrl;
        this.watermarkContractSignUrl$Map = _json(watermarkContractSignUrl, ConfigurationMap.class);
    }
}
