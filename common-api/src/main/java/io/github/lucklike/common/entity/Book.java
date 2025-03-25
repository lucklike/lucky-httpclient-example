package io.github.lucklike.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.github.lucklike.common.api.annotation.ToolParam;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@TableName("BOOK") // 对应 Oracle 表名
public class Book {

    @ToolParam(desc = "书本ID，英文字母和英文符号组成的随机字符串，最大长度为21， 例如：wdre3465yhfdwe23456yu")
    private String id;

    @ToolParam(desc = "书名， 例如：《相对论》")
    private String title;

    @ToolParam(desc = "作者， 例如：爱因斯坦")
    private String author;

    @ToolParam(desc = "书本价格， 例如：13.7", type = "string")
    private BigDecimal price;

    @ToolParam(desc = "书本出版日期，yyyy-MM-dd HH:mm:ss格式字符串 例如：2009-03-24 14:02:05", type = "string")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date publishDate;
}