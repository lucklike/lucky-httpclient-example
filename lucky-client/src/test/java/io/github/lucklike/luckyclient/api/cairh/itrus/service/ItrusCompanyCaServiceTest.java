package io.github.lucklike.luckyclient.api.cairh.itrus.service;

import com.luckyframework.common.StopWatch;
import com.luckyframework.common.StringUtils;
import com.luckyframework.common.TimeUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.FileCopyUtils;

import java.io.File;

import static com.luckyframework.httpclient.proxy.CommonFunctions.base64;
import static com.luckyframework.httpclient.proxy.CommonFunctions.resource;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ItrusCompanyCaServiceTest {

    @Autowired
    private ItrusCompanyCaService service;

    @Test
    void createCAPdf() throws Exception {
        StopWatch sw = new StopWatch();
        sw.start("ca");
        CaRequest request = new CaRequest();
        request.setAgreementsign_id("20241224369070000029");
        request.setMobile_tel("17388998848");
        request.setId_kind("0");
        request.setId_no("320482198805075178");
        request.setUser_name("吴彦祖");
        request.setAgreement_name("彦祖协议");
        request.setCsdc_ent_name("武汉吴彦祖分祖有限公司");
        request.setCsdc_busi_id_no("wuyanzu-001");
        request.setSeal_pos("[{\"height\":\"20\",\"index\":\"5\",\"kw\":\"基金产品\",\"sealcode\":\"2\",\"sealpwd\":\"EB3A43970A54895B\",\"signtype\":\"1\",\"width\":\"20\",\"xOffset\":\"80\",\"yOffset\":\"55\"},{\"height\":\"20\",\"index\":\"5\",\"kw\":\"基金产品\",\"sealcode\":\"2\",\"sealpwd\":\"EB3A43970A54895B\",\"signtype\":\"1\",\"width\":\"20\",\"xOffset\":\"40\",\"yOffset\":\"40\"}]");
        request.setSign_pos("[{\"signtype\":\"1\",\"kw\":\"基金产品\",\"index\":\"5\",\"width\":\"20\",\"height\":\"20\",\"xOffset\":\"80\",\"yOffset\":\"45\"}]");
        request.setFile(FileCopyUtils.copyToByteArray(resource("classpath:pdf/agent.pdf").getInputStream()));
        request.setSeal_image_base64(base64(resource("classpath:pdf/seal.png")));
        request.setReplace_str("{\"date\":\"2019年5月8日\",\"name\":\"xxxx\",\"verifytype\":\"几类专业投资者\",\"account\":\"test1\"}");
        byte[] caPdf = service.createCAPdf(request);
        sw.stopWatch();
        FileCopyUtils.copy(caPdf, new File(StringUtils.format("D:/test/ca/{}_{}.pdf", TimeUtils.format_yyyy_MM_dd(), "caTest")));

        System.out.println(sw.prettyPrintFormat());
    }
}