package io.github.lucklike.luckyclient.api.server.nacos;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.annotation.NacosProperties;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.luckyframework.common.StringUtils;
import com.luckyframework.httpclient.proxy.url.DomainNameContext;
import com.luckyframework.httpclient.proxy.url.DomainNameGetter;
import org.springframework.stereotype.Component;

/**
 * @author fukang
 * @version 1.0.0
 * @date 2025/2/18 23:06
 */
@Component
public class NacosDomainGetter implements DomainNameGetter {

    @NacosInjected(properties = @NacosProperties(namespace = "lucky"))
    private NamingService namingService;

    @Override
    public String getDomainName(DomainNameContext context) throws Exception {
        NacosHttpClient nacosAnn = context.toAnnotation(NacosHttpClient.class);
        String protocol = nacosAnn.protocol();
        String name = nacosAnn.name();
        String group = nacosAnn.group();

        Instance instance = namingService.selectOneHealthyInstance(name, group);
        System.out.println(instance);
        return StringUtils.format("{}://{}:{}", protocol, instance.getIp(), instance.getPort());

    }
}
