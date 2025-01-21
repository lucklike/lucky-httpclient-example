package io.github.lucklike.luckyclient.api.cairh.itrus.service;

import lombok.Data;

@Data
public class Local {

    private Integer signtype;
    private Integer index;
    private Float xOffset;
    private Float yOffset;
    private Float width;
    private Float height;
    private String kw;


    public boolean isKw() {
        return signtype == 0;
    }

}
