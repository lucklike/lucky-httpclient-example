package io.github.lucklike.luckyclient.api.cairh.basedata.resp;


import lombok.Data;

import java.util.List;

@Data
public class PageResponse<T> {
    private Integer pages;
    private Integer current;
    private Integer size;
    private Integer total;
    private List<T> records;
}
