package io.github.lucklike.luckyclient.api.cairh.ttd.resp;

import com.luckyframework.common.ConfigurationMap;
import lombok.Data;

import static com.luckyframework.httpclient.proxy.function.SerializationFunctions._json;


/**
 * @author fukang
 * @version 1.0.0
 * @date 2025/2/16 02:48
 */
@Data
public class Contract {

    private String watermarkSuppleContractSignUrl;
    private ConfigurationMap watermarkSuppleContractSignUrl$Map;
    private String suppleContractUrl;
    private ConfigurationMap suppleContractUrl$Map;
    private String suppleContractVersion;
    private String suppleContractFlowNo;

    public void setWatermarkSuppleContractSignUrl(String watermarkSuppleContractSignUrl) throws Exception {
        this.watermarkSuppleContractSignUrl = watermarkSuppleContractSignUrl;
        this.watermarkSuppleContractSignUrl$Map = _json(watermarkSuppleContractSignUrl, ConfigurationMap.class);
    }

    public void setSuppleContractUrl(String suppleContractUrl) throws Exception {
        this.suppleContractUrl = suppleContractUrl;
        this.suppleContractUrl$Map = _json(suppleContractUrl, ConfigurationMap.class);
    }
}
