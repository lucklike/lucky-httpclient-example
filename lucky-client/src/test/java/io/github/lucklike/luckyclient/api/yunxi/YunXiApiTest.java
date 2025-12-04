package io.github.lucklike.luckyclient.api.yunxi;

import com.luckyframework.httpclient.proxy.spel.SpelBean;
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
}