package io.github.lucklike.luckyclient.api.loosebind;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = true)
public class T3Request extends BaseParam{

    private String cc = "cc";
}
