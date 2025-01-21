package io.github.lucklike.luckyclient.api.cairh.itrus.service;

import io.github.lucklike.luckyclient.api.cairh.itrus.ItrusCommonParam;
import io.github.lucklike.luckyclient.api.cairh.itrus.api.ItrusApi;
import io.github.lucklike.luckyclient.api.cairh.itrus.req.ContractStampListRequest;
import io.github.lucklike.luckyclient.api.cairh.itrus.req.CreateAutographRequest;
import io.github.lucklike.luckyclient.api.cairh.itrus.req.CreateEnterpriseSealRequest;
import io.github.lucklike.luckyclient.api.cairh.itrus.req.QuerySealRequest;
import io.github.lucklike.luckyclient.api.cairh.itrus.resp.CreateAutographResponse;
import io.github.lucklike.luckyclient.api.cairh.itrus.resp.CreateEnterpriseSealResponse;
import io.github.lucklike.luckyclient.api.cairh.itrus.resp.PageResponse;
import io.github.lucklike.luckyclient.api.cairh.itrus.resp.Seal;
import io.github.lucklike.luckyclient.api.cairh.itrus.resp.Stamp;
import lombok.Data;

@Data
public class SealComponent {

    /**
     * 主企业签章ID
     */
    private final Long mainSealId;

    /**
     * 子企业签章ID
     */
    private final Long subSealId;

    /**
     * 个人签章ID
     */
    private final Long signId;


    private SealComponent(ItrusApi itrusApi, ItrusCommonParam param, CaRequest request, UserComponent user) {
        this.mainSealId = param.getCompanySealId();
        this.subSealId = createSubSealId(itrusApi, request, user);
        this.signId = createAutographId(itrusApi, request, user);
    }

    public static SealComponent of(ItrusApi itrusApi, ItrusCommonParam param, CaRequest request, UserComponent user) {
        return new SealComponent(itrusApi, param, request, user);
    }

    /**
     * 获取用户签名ID，先查询，如果存在直接返回，不存在时进行创建
     *
     * @param caRequest CA请求实例
     * @param user      用户信息
     * @return 签名ID
     */
    private Long createAutographId(ItrusApi itrusApi, CaRequest caRequest, UserComponent user) {

        // 先走查询接口查询签名，能查到直接返回
        ContractStampListRequest queryReq = new ContractStampListRequest();
        queryReq.setUserId(user.getUserId());
        queryReq.setName(caRequest.getUser_name());

        PageResponse<Stamp> queryResp = itrusApi.queryStampList(queryReq);
        for (Stamp stamp : queryResp.getList()) {
            Long id = stamp.getId();
            if (id != null) {
                return id;
            }
        }

        //查不到时进行创建
        CreateAutographRequest createReq = new CreateAutographRequest();
        createReq.setUserId(user.getUserId());
        createReq.setAutographType("1");
        createReq.setAutographName(caRequest.getUser_name());
        CreateAutographResponse createResp = itrusApi.createAutograph(createReq);
        return createResp.getAutographId();
    }

    /**
     * 获取印章ID，先查询，如果存在直接返回，不存在时进行创建
     *
     * @param caRequest CA请求实例
     * @return 印章ID
     */
    private Long createSubSealId(ItrusApi itrusApi, CaRequest caRequest, UserComponent user) {

        // 先走查询接口查询印章，能查到直接返回
        QuerySealRequest queryReq = new QuerySealRequest();
        queryReq.setEnterpriseId(user.getSubCompanyId());
        PageResponse<Seal> queryResp = itrusApi.querySealList(queryReq);
        for (Seal seal : queryResp.getList()) {
            Long id = seal.getId();
            if (id != null) {
                return id;
            }
        }

        // 查不到时进行创建
        CreateEnterpriseSealRequest request = new CreateEnterpriseSealRequest();
        request.setUserId(user.getUserId());
        request.setEnterpriseId(user.getSubCompanyId());
        // 自定义方式创建
        request.setSealType(2);
        request.setSealName(caRequest.getCsdc_ent_name());
        request.setSealBase64(caRequest.getSeal_image_base64());
        CreateEnterpriseSealResponse response = itrusApi.createEnterpriseSeal(request);
        return response.getSealId();
    }
}
