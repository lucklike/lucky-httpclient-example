package io.github.lucklike.luckyclient.api.cairh.itrus.service;

import io.github.lucklike.luckyclient.api.cairh.itrus.ItrusCommonParam;
import io.github.lucklike.luckyclient.api.cairh.itrus.api.ItrusApi;
import io.github.lucklike.luckyclient.api.cairh.itrus.req.AddSubCompanyRequest;
import io.github.lucklike.luckyclient.api.cairh.itrus.req.CreateUserRequest;
import io.github.lucklike.luckyclient.api.cairh.itrus.req.EnterpriseCreateRequest;
import io.github.lucklike.luckyclient.api.cairh.itrus.req.QueryEnterpriseRequest;
import io.github.lucklike.luckyclient.api.cairh.itrus.resp.CreateUserResponse;
import io.github.lucklike.luckyclient.api.cairh.itrus.resp.EnterpriseCreateResponse;
import io.github.lucklike.luckyclient.api.cairh.itrus.resp.QueryEnterpriseResponse;
import lombok.Data;
import org.springframework.util.StringUtils;

@Data
public class UserComponent {

    private final String userId;
    private final String mainCompanyUserId;
    private final String mainCompanyId;
    private final String subCompanyId;


    private UserComponent(ItrusApi itrusApi, ItrusCommonParam param, CaRequest request) {
        this.userId = createUser(itrusApi, request);
        this.mainCompanyId = param.getCompanyUUID();
        this.mainCompanyUserId = param.getCompanyCreatorId();
        this.subCompanyId = createSubCompanyId(itrusApi, request);
    }

    public static UserComponent of(ItrusApi itrusApi, ItrusCommonParam param, CaRequest request) {
        return new UserComponent(itrusApi, param, request);
    }

    /**
     * 创建子企业
     *
     * @param itrusApi  天威API
     * @param caRequest 请求实例
     * @return 子企业ID
     */
    private String createSubCompanyId(ItrusApi itrusApi, CaRequest caRequest) {

        // 先执行查询，如果可以查到企业ID则直接返回
        QueryEnterpriseRequest queryReq = new QueryEnterpriseRequest();
        queryReq.setSocialCode(caRequest.getCsdc_busi_id_no());
        queryReq.setAcompanyName(caRequest.getCsdc_ent_name());
        QueryEnterpriseResponse pageOrg = itrusApi.queryEnterpriseList(queryReq);

        for (QueryEnterpriseResponse.Org org : pageOrg.getCompanyList()) {
            String companyUuid = org.getCompanyUuid();
            if (StringUtils.hasText(companyUuid)) {
                return companyUuid;
            }
        }

        // 查询不到企业ID时则创建
        EnterpriseCreateRequest request = new EnterpriseCreateRequest();
        request.setUserId(userId);
        request.setAuthentication(true);
        request.setOrgCode(caRequest.getCsdc_busi_id_no());
        request.setOrgName(caRequest.getCsdc_ent_name());
        EnterpriseCreateResponse response = itrusApi.createEnterprise(request);
        String enterpriseId = response.getEnterpriseId();

        // 创建成功之后将该企业添加到主企业下
        addSubCompany(itrusApi, enterpriseId);
        return enterpriseId;
    }

    /**
     * 获取合同用户ID
     *
     * @param caRequest CA请求对象
     * @return 合同用户ID
     */
    private String createUser(ItrusApi itrusApi, CaRequest caRequest) {
        CreateUserRequest apiRequest = new CreateUserRequest();
        apiRequest.setType("1");
        apiRequest.setPhone(caRequest.getMobile_tel());
        apiRequest.setAuthentication("true");
        apiRequest.setIdCardType("0");
        apiRequest.setIdCardNum(caRequest.getId_no());
        apiRequest.setDisplayName(caRequest.getUser_name());
        CreateUserResponse response = itrusApi.createUser(apiRequest);
        return response.getUserId();
    }

    /**
     * 将子企业添加到主企业下
     */
    private void addSubCompany(ItrusApi itrusApi, String enterpriseId) {
        AddSubCompanyRequest request = new AddSubCompanyRequest();
        request.setEnterpriseId(mainCompanyId);
        request.setSubCompanyUuid(enterpriseId);
        itrusApi.addSubCompany(request);
    }


}
