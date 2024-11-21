package io.github.lucklike.luckyclient.api.cairh.hehe.resp;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class BankCardResponse extends HeheBaseResp {

    private String type;
    private String card_number;
    private String issuer;
    private String issuer_id;
    private String validate;
    private String holder_name;

}
