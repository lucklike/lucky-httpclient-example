package io.github.lucklike.luckyclient.api.cairh.comp.verify.req;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 公安认证配置
 */
@Data
@Accessors(chain = true)
public class PoliceVerifyConfig {

    /**
     * 流水号
     */
    private String serial_id;

    /**
     * 公安认证接口工厂名称
     */
    private String factory_name;

    private String factory_name_tranfer;

    /**
     * 优先级
     */
    private String priority_level;

    /**
     * 状态
     */
    private String status;

    private String status_tranfer;

    /**
     * 是否高清
     */
    private String is_high_definition;


    /**
     * 时间名称ID
     */
    private String time_kind;

    private String time_kind_tranfer;


    /**
     * 适用子系统
     */
    private String subsys_id;

    private String subsys_id_tranfer;


    /**
     * 发布人
     */
    private String create_by;

    private String create_by_tranfer;

    /**
     * 修改人
     */
    private String modify_by;

    private String modify_by_tranfer;

    /**
     * 创建日期时间(申请时间查询条件)
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date create_datetime;

    /**
     * 修改时间(作为发布时间，不会随修改数据而改动)
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date modify_datetime;

}
