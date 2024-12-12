package io.github.lucklike.luckyclient.api.cairh.basedata.req;

import io.github.lucklike.luckyclient.api.cairh.basedata.resp.RareWordResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = true)
public class RareWordRequest extends RareWordResponse {

    private Integer current = 1;
    private Integer size = 10;

}
