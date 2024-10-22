package io.github.lucklike.luckyclient.api.cairh.request;

import io.github.lucklike.luckyclient.api.cairh.response.RareWordResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = true)
public class RareWordRequest extends RareWordResponse {

    private Integer current = 1;
    private Integer size = 10;

}
