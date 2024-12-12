package io.github.lucklike.luckyclient.api.cairh.basedata.resp;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class RareWordResponse {
    /**
     * 序列号
     */
    private String serial_id;

    /**
     *
     */
    private String character_str;

    /**
     * 不带音调的拼音
     */
    private String first_pinyin;

    /**
     * 带音调的拼音
     */
    private String second_pinyin;

    /**
     * 进行unicode编码之后的生僻字
     */
    private String append_string;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date create_datetime;

    /**
     * 创建人
     */
    private String create_by;

    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date update_datetime;

    /**
     * 修改人
     */
    private String update_by;

    /**
     * 备注
     */
    private String remark;

    /**
     * 原始字符串
     */
    private String source_append_string;

    /**
     * 原始带音调的额拼音
     */
    private String source_second_pinyin;
}
