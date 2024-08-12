package io.github.lucklike.luckyclient.api.kuaitong;

import cn.hutool.core.date.DateUtil;
import lombok.Data;

import java.util.Date;

@Data
public class Token {
    private String access_token;
    private Long expires_in;
    private Date expires_time;


    public boolean isExpires() {
        return new Date().after(expires_time);
    }


    public void generateExpiresTime() {
        this.expires_time = DateUtil.offsetSecond(new Date(), expires_in.intValue());
    }
}
