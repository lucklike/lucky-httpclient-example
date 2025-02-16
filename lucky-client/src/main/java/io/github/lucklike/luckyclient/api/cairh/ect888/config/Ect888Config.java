package io.github.lucklike.luckyclient.api.cairh.ect888.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Ect888 API配置
 */
@Data
@Component
@ConfigurationProperties(prefix = "ect888")
public class Ect888Config {

    /**
     * 公钥
     */
    private String publicKey;

    /**
     * 访问地址
     */
    private String url;

    /**
     * 机构账号
     */
    private String ptyAcct;

    /**
     * 机构号
     */
    private String ptyCd;

    /**
     * 会话秘钥需16位-256位之间
     */
    private String ptyKey;

    /**
     * 旧密码
     */
    private String ptyPwd;

    /**
     * 新密码
     */
    private String newPtyPwd;

    /**
     * 代理域名
     */
    private String proxyHost;

    /**
     * 代理端口
     */
    private int proxyPort;
}
