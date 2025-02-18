package io.github.lucklike.luckyclient.nacos;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;

import java.util.Properties;

public class NacosDemo {

    public static void main(String[] args) throws NacosException {
        Properties properties = new Properties();
        properties.setProperty(PropertyKeyConst.SERVER_ADDR, "111.229.161.129:8848");
        properties.setProperty(PropertyKeyConst.NAMESPACE, "cpe-base");
        properties.setProperty(PropertyKeyConst.USERNAME, "nacos");
        properties.setProperty(PropertyKeyConst.PASSWORD, "nacos");


        NamingService namingService = NacosFactory.createNamingService(properties);

        Instance instance = namingService.selectOneHealthyInstance("cpe-products-basedata-backend", "cpe-group");

        System.out.println(instance);
    }
}
