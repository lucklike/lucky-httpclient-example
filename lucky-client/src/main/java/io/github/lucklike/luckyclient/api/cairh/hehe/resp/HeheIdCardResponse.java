package io.github.lucklike.luckyclient.api.cairh.hehe.resp;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class HeheIdCardResponse {
    private Integer error_code;
    private String error_msg;
    private String type;
    private String name;
    private String sex;
    private String people;
    private String birthday;
    private String address;
    private String id_number;
    private String issue_authority;
    private String validity;
    private Boolean head_covered;
    private Boolean head_blurred;
    private Boolean complete;
    private Boolean border_covered;
    private Boolean gray_image;
    private Time_cost time_cost;
}
@Data
class Time_cost{
    Integer preprocess;
    Integer recognize;
}
