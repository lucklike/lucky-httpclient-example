package io.github.lucklike.luckyclient.nacos;

import lombok.Data;

import java.util.Map;

@Data
public class NacosConfigProperties {

    private String serverAddr;
    private String username;
    private String password;
    private String namespace;
    private Map<String, ServiceInfo> services;

}
