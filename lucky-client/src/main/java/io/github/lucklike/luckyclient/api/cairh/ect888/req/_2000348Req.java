package io.github.lucklike.luckyclient.api.cairh.ect888.req;

import io.github.lucklike.luckyclient.api.cairh.ect888.ann.EctAcct;
import io.github.lucklike.luckyclient.api.cairh.ect888.ann.EctCd;
import io.github.lucklike.luckyclient.api.cairh.ect888.ann.EctId;
import io.github.lucklike.luckyclient.api.cairh.ect888.ann.EctSign;
import io.github.lucklike.luckyclient.api.cairh.ect888.ann.EctTime;
import lombok.Data;


@Data
public class _2000348Req {

    private String funcNo = "2000348";
    private String name;

    @EctId
    @EctSign
    private String id;

    @EctSign
    private String sourcechnl = "0";

    @EctSign
    private String placeid ="00";

    @EctSign
    private String biztyp = "A001";

    @EctSign
    private String biztypdesc = "证券失信查询";

    @EctCd
    @EctSign
    private String ptycd;

    @EctAcct
    @EctSign
    private String ptyacct;

    @EctTime
    @EctSign
    private String timestamp;

}
