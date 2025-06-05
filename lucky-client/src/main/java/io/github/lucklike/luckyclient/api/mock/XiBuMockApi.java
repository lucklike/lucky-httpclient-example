package io.github.lucklike.luckyclient.api.mock;

import com.luckyframework.httpclient.proxy.annotations.Post;
import com.luckyframework.httpclient.proxy.annotations.RespConvert;
import com.luckyframework.httpclient.proxy.annotations.Wrapper;
import com.luckyframework.httpclient.proxy.mock.Mock;
import io.github.lucklike.httpclient.discovery.HttpClient;

import java.util.List;
import java.util.Map;
import java.util.Set;

@HttpClient
public interface XiBuMockApi {

    @RespConvert("#{$body$.data.rows}")
    @Mock(body = "#{#file('D:/QYWX/Files/WXWork/1688857280578806/Cache/File/2025-04/西部接口返回的经纪人.json')}")
    @Post("http://www.xb.com/broker/list")
    List<Map<String, Object>> black();

    @Wrapper("#{$this$.black().![RYID]}")
    List<String> brokerIdList();

    @Wrapper("#{$this$.black().![RYID]}")
    Set<String> brokerIdSet();

    @Wrapper("#{$this$.black().![JTDH]}")
    Set<String> brokerTelSet();

    @Wrapper("#{$this$.black().![JTDH]}")
    List<String> brokerTelList();


    @Wrapper("#{$this$.black().![JTDH + '_' + RYID]}")
    List<String> brokerIds();
}
