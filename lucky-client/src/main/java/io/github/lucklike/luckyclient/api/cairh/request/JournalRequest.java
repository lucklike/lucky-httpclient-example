package io.github.lucklike.luckyclient.api.cairh.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;


@Data
public class JournalRequest {

    /**
     * 修改人
     */
    private String modify_by;

    /**
     * 开始时间
     */
    private String start_time;

    /**
     * 结束时间
     */
    private String end_time;

    /**
     * 表名
     */
    private String table_name;


    /**
     * 字段名
     */
    private String column_name;

    /**
     * 字段内容
     */
    private String column_context;

    protected long size;
    protected long current;
}
