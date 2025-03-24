package io.github.lucklike.common.entity;

import io.github.lucklike.common.api.annotation.FunctionCallDesc;
import lombok.Data;

@Data
public class PageBookRequest {

    @FunctionCallDesc(desc = "分页参数，当前页码，例如：1", type = "string")
    private Long pageNum = 1L;

    @FunctionCallDesc(desc = "分页参数，每页展示的条数，例如：10", type = "string")
    private Long pageSize = 10L;

    @FunctionCallDesc(desc = "书名， 例如：《相对论》")
    private String title;

    @FunctionCallDesc(desc = "作者， 例如：爱因斯坦")
    private String author;
}
