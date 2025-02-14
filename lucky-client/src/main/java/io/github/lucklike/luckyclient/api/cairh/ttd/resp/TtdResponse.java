package io.github.lucklike.luckyclient.api.cairh.ttd.resp;

import lombok.Data;

import java.util.List;

@Data
public class TtdResponse<T> {

    private Integer code;
    private String msg;
    private DataObj<T> data;

    @Data
    public static class DataObj<T> {
        private Integer isArray = 1;
        private Object bean;
        private List<T> list;
        private Integer total = 0;
        private Integer totalPage = 0;
        private Integer currentPage = 0;
        private Integer pageSize = 10;
    }
}
