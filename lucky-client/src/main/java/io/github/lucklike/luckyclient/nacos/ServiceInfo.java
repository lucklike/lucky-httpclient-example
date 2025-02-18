package io.github.lucklike.luckyclient.nacos;

import lombok.Data;

@Data
public class ServiceInfo {
    private String group;
    private String serviceName;
}
