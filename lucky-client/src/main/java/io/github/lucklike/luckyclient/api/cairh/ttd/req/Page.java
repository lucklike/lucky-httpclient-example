package io.github.lucklike.luckyclient.api.cairh.ttd.req;

import lombok.Data;

/**
 * @author fukang
 * @version 1.0.0
 * @date 2025/2/16 02:34
 */
@Data
public class Page {

    private final Integer pageNum;
    private final Integer pageSize;

    private Page(Integer pageNum, Integer pageSize) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public static Page of(Integer pageNum, Integer pageSize) {
        return new Page(pageNum, pageSize);
    }
}
