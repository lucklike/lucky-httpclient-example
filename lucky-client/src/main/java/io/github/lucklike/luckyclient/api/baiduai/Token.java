package io.github.lucklike.luckyclient.api.baiduai;

import cn.hutool.core.date.DateUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class Token {

    /**
     * 访问凭证
     */
    private String access_token;

    /**
     * 有效期，Access Token的有效期。
     * 说明：单位是秒，有效期30天
     */
    private Integer expires_in;

    /**
     * 错误码
     * 说明：响应失败时返回该字段，成功时不返回
     */
    private String error;

    /**
     * 错误描述信息，帮助理解和解决发生的错误
     * 说明：响应失败时返回该字段，成功时不返回
     */
    private String error_description;

    /**
     * 扩展字段，暂时未使用，可忽略
     */
    private String session_key;

    /**
     * 扩展字段，暂时未使用，可忽略
     */
    private String refresh_token;

    /**
     * 扩展字段，暂时未使用，可忽略
     */
    private String scope;

    /**
     * 扩展字段，暂时未使用，可忽略
     */
    private String session_secret;

    /**
     * 过期时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date expires_time;

    public boolean isExpires() {
        return new Date().after(expires_time);
    }

    public void generateExpiresTime() {
        this.expires_time = DateUtil.offsetSecond(new Date(), expires_in);
    }

}
