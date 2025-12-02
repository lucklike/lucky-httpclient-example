package io.github.lucklike.luckyclient.api.cairh.itrus.api;

import com.luckyframework.httpclient.proxy.function.CommonFunctions;
import io.github.lucklike.luckyclient.api.cairh.itrus.ItrusCommonParam;
import io.github.lucklike.luckyclient.api.cairh.itrus.req.CreateEnterpriseSealRequest;
import io.github.lucklike.luckyclient.api.cairh.itrus.req.QueryEnterpriseRequest;
import io.github.lucklike.luckyclient.api.cairh.itrus.req.QuerySealRequest;
import io.github.lucklike.luckyclient.api.cairh.itrus.resp.CreateEnterpriseSealResponse;
import io.github.lucklike.luckyclient.api.cairh.itrus.resp.PageResponse;
import io.github.lucklike.luckyclient.api.cairh.itrus.resp.QueryEnterpriseResponse;
import io.github.lucklike.luckyclient.api.cairh.itrus.resp.Seal;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.FileCopyUtils;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.luckyframework.httpclient.proxy.function.CommonFunctions.resource;
import static com.luckyframework.httpclient.proxy.function.SerializationFunctions._base64;
import static com.luckyframework.httpclient.proxy.function.SerializationFunctions.base64;


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

        // 先执行查询，如果可以查到企业ID则直接返回
        QueryEnterpriseRequest queryReq = new QueryEnterpriseRequest();
        queryReq.setEnterpriseId(param.getCompanyUUID());
        QueryEnterpriseResponse pageOrg = itrusApi.queryEnterpriseList(queryReq);
        System.out.println(pageOrg);
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
        queryReq.setUserId(param.getCompanyCreatorId());
        queryReq.setRequiredBase64("true");
        queryReq.setPageSize("50");
        PageResponse<Seal> queryResp = itrusApi.querySealList(queryReq);

        List<Seal> sealList = new ArrayList<>(queryResp.getList());
        queryReq.setPageNum("2");
        sealList.addAll(itrusApi.querySealList(queryReq).getList());

        for (Seal seal : sealList) {
            if (Objects.nonNull(seal.getSealFile())) {
                File file = new File("D:/test/ca/seal", seal.getId() + ".jpg");
                FileCopyUtils.copy(_base64(seal.getSealFile()), file);
            }
        }
        System.out.println(queryResp);
    }

    @Test
    void createEnterpriseSeal() throws IOException {
        CreateEnterpriseSealRequest request = new CreateEnterpriseSealRequest();
        request.setUserId(param.getCompanyCreatorId());
        request.setEnterpriseId(param.getCompanyUUID());
        // 自定义方式创建
        request.setSealType(2);
        request.setSealName("测试公章-00123");
        request.setSealBase64(base64(resource("file:/Users/fukang/Desktop/test/ca/seal/167044677231465389.jpg")));
        CreateEnterpriseSealResponse response = itrusApi.createEnterpriseSeal(request);
        System.out.println(response);
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