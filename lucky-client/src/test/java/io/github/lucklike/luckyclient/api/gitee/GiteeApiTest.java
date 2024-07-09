package io.github.lucklike.luckyclient.api.gitee;

import io.github.lucklike.entity.request.gitee.Stargazers;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author fukang
 * @version 1.0.0
 * @date 2024/7/9 01:33
 */
@SpringBootTest
public class GiteeApiTest {

    @Resource
    private GiteeApi api;

    @Test
    void stargazersTest(){
        List<Stargazers> stargazers = api.stargazers("dromara", "forest");
        stargazers.forEach(System.out::println);
    }
}
