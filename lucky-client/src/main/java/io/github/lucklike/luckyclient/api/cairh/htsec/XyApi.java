package io.github.lucklike.luckyclient.api.cairh.htsec;

import com.luckyframework.httpclient.proxy.CommonFunctions;
import com.luckyframework.httpclient.proxy.annotations.Post;
import com.luckyframework.httpclient.proxy.annotations.PrintRequestLog;
import com.luckyframework.httpclient.proxy.annotations.PropertiesJson;
import com.luckyframework.httpclient.proxy.annotations.RespConvert;
import com.luckyframework.httpclient.proxy.annotations.Wrapper;
import io.github.lucklike.httpclient.discovery.HttpClient;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@PrintRequestLog
@HttpClient("${cairh.htxy.url}")
public interface XyApi {

    List<String> backNoList = Arrays.asList(
            "021816",
            "021803",
            "021801",
            "021828",
            "021804",
            "021815",
            "021802",
            "021806"
    );

    @RespConvert("#{$body$.ret_data.?[#backData(#this.bank_code, #this.order_type, #this.protocol_name)]}")
    @PropertiesJson("app_id=${cairh.htxy.appId}")
    @Post("/EeamsWeb/json/service/queryPTOList.do")
    List<Object> getXyData();


    static boolean backData(String backNo, String order_type, String aName) {
        if  (!Objects.equals(backNo, "021804")) {
            return false;
        }
        if (aName.contains("机构")) {
            return false;
        }
        if(order_type.toUpperCase().startsWith("RZRQ")) {
            return false;
        }
        return true;
    }

}
