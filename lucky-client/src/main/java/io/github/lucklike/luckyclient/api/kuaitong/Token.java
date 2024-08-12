package io.github.lucklike.luckyclient.api.kuaitong;

import cn.hutool.core.date.DateUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class Token {
    private String access_token;
    private Long expires_in;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date expires_time;


    public boolean isExpires() {
        return new Date().after(expires_time);
    }


    public void generateExpiresTime() {
        this.expires_time = DateUtil.offsetSecond(new Date(), expires_in.intValue());
    }
}
