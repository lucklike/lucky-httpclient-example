package io.github.lucklike.luckyclient.api.cairh.itrus.service;

import io.github.lucklike.luckyclient.api.cairh.itrus.req.AddSignerRequest;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

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

    /**
     * 转化为位置控件
     *
     * @param type 签署类型
     * @param id   控件ID
     * @return 位置控件
     */
    public AddSignerRequest.XySignControl toXySignControl(String type, int id) {
        AddSignerRequest.XySignControl xySignControl = new AddSignerRequest.XySignControl();
        xySignControl.setId(id);
        xySignControl.setType(type);
        xySignControl.setPageNum(index.toString());
        xySignControl.setX(String.valueOf(xOffset / 100f));
        xySignControl.setY(String.valueOf(yOffset / 100f));
        if (height != null) {
            xySignControl.setHeight(height);
        }
        if (width != null) {
            xySignControl.setWidth(width);
        }
        return xySignControl;
    }

    /**
     * 转化为关键字控件
     *
     * @param type 签署类型
     * @param id   控件ID
     * @return 关键字控件
     */
    public AddSignerRequest.KeywordSignControl toKwSignControl(String type, int id) {
        AddSignerRequest.KeywordSignControl keywordSignControl = new AddSignerRequest.KeywordSignControl();
        keywordSignControl.setId(id);
        keywordSignControl.setType(type);
        keywordSignControl.setPageNum(index.toString());
        keywordSignControl.setOffsetX(xOffset.toString());
        keywordSignControl.setOffsetY(yOffset.toString());
        keywordSignControl.setKeyword(kw);
        if (height != null) {
            keywordSignControl.setHeight(height);
        }
        if (width != null) {
            keywordSignControl.setWidth(width);
        }
        return keywordSignControl;
    }
}
