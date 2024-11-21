package io.github.lucklike.luckyclient.api.cairh.hehe.resp;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class BusinessLicenseResponse extends HeheBaseResp{
    private String type;
    private String biz_license_credit_code;
    private String biz_license_company_name;
    private String biz_license_company_type;
    private String biz_license_address;
    private String biz_license_owner_name;
    private String biz_license_reg_capital;
    private String biz_license_start_time;
    private String biz_license_operating_period;
    private String biz_license_scope;
    private String biz_license_registration_date;
    private String biz_license_composing_form;
    private String biz_license_serial_number;
    private String biz_license_registration_authority;
    private String biz_license_registration_code;
    private String biz_license_paidin_capital;
    private String biz_license_is_copy;
    private String biz_license_is_electronic;
}
