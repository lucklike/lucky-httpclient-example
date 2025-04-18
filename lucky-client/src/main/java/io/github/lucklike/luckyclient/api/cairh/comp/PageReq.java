package io.github.lucklike.luckyclient.api.cairh.comp;

import lombok.Data;

@Data
public class PageReq {
    protected long size = 10;
    protected long current = 1;
}
