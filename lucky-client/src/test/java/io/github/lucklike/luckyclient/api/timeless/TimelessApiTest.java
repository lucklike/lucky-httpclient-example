package io.github.lucklike.luckyclient.api.timeless;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.IOException;

@SpringBootTest
class TimelessApiTest {

    @Resource
    private TimelessApi timelessApi;


    @Test
    void testQryLyrics() throws IOException {
        timelessApi.lyrics();
    }

    @Test
    void testqQuerySongId() {
        String s = timelessApi.querySongId("");
        System.out.println(s);
    }

}