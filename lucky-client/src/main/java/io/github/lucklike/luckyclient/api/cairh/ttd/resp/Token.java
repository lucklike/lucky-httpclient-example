package io.github.lucklike.luckyclient.api.cairh.ttd.resp;

import cn.hutool.core.date.DateUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.luckyframework.httpclient.generalapi.token.TokenResult;
import lombok.Data;

import java.util.Date;

/**
 * Token
 * @author fukang
 * @version 1.0.0
 * @date 2025/12/25 00:38
 */
@Data
public class Token implements TokenResult {

    private String access_token;
    private Integer expires_in;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date expires_at;

    @Override
    public boolean expires() {
        return expires_at.before(new Date());
    }

    @Override
    public void postProcess() {
        this.expires_at = DateUtil.offsetSecond(new Date(), expires_in);
    }
}
