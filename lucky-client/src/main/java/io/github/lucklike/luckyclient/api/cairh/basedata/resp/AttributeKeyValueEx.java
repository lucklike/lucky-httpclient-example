package io.github.lucklike.luckyclient.api.cairh.basedata.resp;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class AttributeKeyValueEx {

    //扩展信息KEY
    private String attr_key;

    //扩展信息VALUE
    private String attr_value;

    //中文描述
    private String attr_label_sys;
}