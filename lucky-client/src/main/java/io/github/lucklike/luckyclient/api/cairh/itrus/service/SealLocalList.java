package io.github.lucklike.luckyclient.api.cairh.itrus.service;

import com.alibaba.fastjson.JSON;
import com.luckyframework.common.StringUtils;
import io.github.lucklike.luckyclient.api.cairh.BizException;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SealLocalList {

    /**
     * 签名位置
     */
    private List<Local> signLocals = new ArrayList<>();

    /**
     * 印章位置
     */
    private List<Local> sealLocals = new ArrayList<>();


    public static SealLocalList forCaRequest(CaRequest caRequest) {
        SealLocalList list = new SealLocalList();
        String sealPos = caRequest.getSeal_pos();
        String signPos = caRequest.getSign_pos();

        if (StringUtils.hasText(sealPos)) {
            list.setSignLocals(JSON.parseArray(sealPos, Local.class));
        }

        if (StringUtils.hasText(signPos)) {
            list.setSealLocals(JSON.parseArray(signPos, Local.class));
        }
        list.check();
        return list;
    }

    public int getSize() {
        return signLocals.size() + sealLocals.size();
    }

    public boolean hasSignLocal() {
        return !signLocals.isEmpty();
    }

    public boolean hasSealLocal() {
        return !sealLocals.isEmpty();
    }

    public void check() {
        if (getSize() == 0) {
            throw new BizException("未找到签名区域配置seal_pos和签名区域配置sign_pos");
        }
    }
}
