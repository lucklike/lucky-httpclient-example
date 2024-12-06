package io.github.lucklike.luckyclient.api.timeless;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

@SpringBootTest
class TimelessApiTest {

    @Resource
    private TimelessApi timelessApi;


    @Test
    void testQryLyrics() throws IOException {
        timelessApi.lyrics();
    }

    @Test
    void testDistrict() {
        List<District> districtList = timelessApi.district();
        System.out.println(districtList);
    }

}