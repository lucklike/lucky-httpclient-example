package io.github.lucklike.luckyclient.api.cairh.itrus.resp;

import lombok.Data;

import java.util.List;

/**
 * 分页响应对象
 * @param <T>
 */
@Data
public class PageResponse<T> {

    private Integer pageNumber;

    private Integer pageSize;

    private Integer totalPage;

    private Integer totalRecord;

    private List<T> list;

}
