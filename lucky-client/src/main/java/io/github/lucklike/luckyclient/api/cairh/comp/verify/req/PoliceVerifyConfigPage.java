package io.github.lucklike.luckyclient.api.cairh.comp.verify.req;

import io.github.lucklike.luckyclient.api.cairh.comp.PageReq;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class PoliceVerifyConfigPage extends PageReq {

    /**
     * 公安认证接口工厂名称
     */
    private String factory_name;

    /**
     * 状态
     */
    private String status;

    /**
     * 是否高清
     */
    private String is_high_definition;

    /**
     * 时间种类
     */
    private String time_kind;

    /**
     * 子系统编号
     */
    private String subsys_id;

}
