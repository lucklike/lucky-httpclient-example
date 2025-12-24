package io.github.lucklike.luckyclient.api.yunxi;

import com.luckyframework.httpclient.proxy.spel.SpelBean;
import com.luckyframework.spel.SimpleSpelBean;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author fukang
 * @version 1.0.0
 * @date 2025/12/5 00:39
 */
@SpringBootTest
class YunXiApiTest {

    @Resource
    private  YunXiApi yunXiApi;

    @Test
    void weiboTop() {
        String[] tops = yunXiApi.weiboTop();
        Stream.of(tops).forEach(System.out::println);
    }

    @Test
    void getProxyIp() {
        String[] proxyIps = yunXiApi.getProxyIp();
        Stream.of(proxyIps).forEach(System.out::println);
    }

    @Test
    void getProxyIpSimpleSpelBean() {
        SimpleSpelBean<?> spelBean = yunXiApi.getProxyIpSimpleSpelBean();
        System.out.println(spelBean.getInt("code"));
        System.out.println(spelBean.getBoolean("msg"));
        System.out.println(spelBean.getString("data[0].time"));
    }

    @Test
    void getProxyIpSpelBean() {
        SpelBean<?> spelBean = yunXiApi.getProxyIpSpelBean();
        System.out.println(spelBean.getInt("code"));
        System.out.println(spelBean.getBoolean("msg"));
        System.out.println(spelBean.getString("data[0].time"));
    }
}