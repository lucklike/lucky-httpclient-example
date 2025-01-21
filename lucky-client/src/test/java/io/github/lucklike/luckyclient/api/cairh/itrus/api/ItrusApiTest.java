package io.github.lucklike.luckyclient.api.cairh.itrus.api;

import com.luckyframework.httpclient.proxy.CommonFunctions;
import io.github.lucklike.luckyclient.api.cairh.itrus.ItrusCommonParam;
import io.github.lucklike.luckyclient.api.cairh.itrus.req.QuerySealRequest;
import io.github.lucklike.luckyclient.api.cairh.itrus.resp.PageResponse;
import io.github.lucklike.luckyclient.api.cairh.itrus.resp.Seal;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.FileCopyUtils;

import javax.annotation.Resource;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ItrusApiTest {

    @Resource
    private ItrusApi itrusApi;
    @Resource
    private ItrusCommonParam param;

    @Test
    void createUser() {
    }

    @Test
    void queryEnterpriseList() {
    }

    @Test
    void createEnterprise() {
    }

    @Test
    void queryStampList() {
    }

    @Test
    void createAutograph() {
    }

    @Test
    void querySealList() throws IOException {
        // 先走查询接口查询印章，能查到直接返回
        QuerySealRequest queryReq = new QuerySealRequest();
        queryReq.setEnterpriseId(param.getCompanyUUID());
        queryReq.setRequiredBase64("true");
        queryReq.setPageSize("50");
        PageResponse<Seal> queryResp = itrusApi.querySealList(queryReq);
        for (Seal seal : queryResp.getList()) {
            File file = new File("D:/test/ca/seal", seal.getId() + ".jpg");
            FileCopyUtils.copy(CommonFunctions._base64(seal.getSealFile()), file);
        }
        System.out.println(queryResp);
    }

    @Test
    void createEnterpriseSeal() {
    }

    @Test
    void addSubCompany() {
    }

    @Test
    void createFileContract() {
    }

    @Test
    void addSignerByFile() {
    }

    @Test
    void signFileContract() {
    }

    @Test
    void downloadContract() {
    }
}