package io.github.lucklike.luckyclient.api.cairh.openapi;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.luckyframework.httpclient.generalapi.token.TokenResult;
import lombok.Data;

import java.util.Date;

@Data
public class Token implements TokenResult {

    /**
     * 访问Token
     */
    private String accessToken;

    /**
     * 过期时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date expiresAt;

    @Override
    public boolean expires() {
        return expiresAt.before(new Date());
    }

}
