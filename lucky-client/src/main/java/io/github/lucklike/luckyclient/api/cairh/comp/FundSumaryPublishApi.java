package io.github.lucklike.luckyclient.api.cairh.comp;

import com.luckyframework.httpclient.core.meta.Response;
import com.luckyframework.httpclient.proxy.annotations.MultiFile;
import com.luckyframework.httpclient.proxy.annotations.Post;
import com.luckyframework.httpclient.proxy.annotations.QueryParam;
import com.luckyframework.httpclient.proxy.mock.Mock;
import com.luckyframework.httpclient.proxy.mock.MockResponse;
import com.luckyframework.reflect.Param;
import io.github.lucklike.httpclient.discovery.HttpClient;
import io.github.lucklike.luckyclient.api.cairh.comp.req.FundSumaryPublishResult;

@HttpClient("http://localhost:8080/elecagreemodel")
public interface FundSumaryPublishApi {

    @Mock
    @Post("fundSumaryPublish")
    FundSumaryPublishResult publish(@QueryParam String agreement_type, @MultiFile String files);


    static Response publish$Mock(@Param("#{agreement_type}") String agreement_type) {
        FundSumaryPublishResult result = FundSumaryPublishResult.create(3);
        result.addFailDetails(FundSumaryPublishResult.Details.failByFileNameIllegality("海通证券股份有限公司创业板融资融券交易风险揭示书-1.pdf"));
        String fileName = "CN_50300001_001468_FA010070_20200002_123109_20200728_111213_01.pdf";
        String format = String.format("基金产品资料概要协议布成功！文件名：%s,业务类型：%s", fileName, agreement_type);
        result.addSuccessDetails(FundSumaryPublishResult.Details.create(fileName, format));

        result.addFailDetails(FundSumaryPublishResult.Details.failByProcessed("CN_50300001_001468_FA010070_20200002_123108_20200728_111213_02.pdf"));

        return MockResponse.create().status(200).json(result);
    }
}
