package io.github.lucklike.luckyclient.api.cairh.itrus.api;

import com.luckyframework.httpclient.generalapi.describe.Describe;
import com.luckyframework.httpclient.proxy.annotations.JsonBody;
import com.luckyframework.httpclient.proxy.annotations.Post;
import com.luckyframework.httpclient.proxy.annotations.PrintLog;
import com.luckyframework.httpclient.proxy.annotations.Retryable;
import io.github.lucklike.luckyclient.api.cairh.BizException;
import io.github.lucklike.luckyclient.api.cairh.itrus.ItrusHttpClient;
import io.github.lucklike.luckyclient.api.cairh.itrus.req.AddSignerRequest;
import io.github.lucklike.luckyclient.api.cairh.itrus.req.AddSubCompanyRequest;
import io.github.lucklike.luckyclient.api.cairh.itrus.req.ContractSignRequest;
import io.github.lucklike.luckyclient.api.cairh.itrus.req.ContractStampListRequest;
import io.github.lucklike.luckyclient.api.cairh.itrus.req.CreateAutographRequest;
import io.github.lucklike.luckyclient.api.cairh.itrus.req.CreateContractRequest;
import io.github.lucklike.luckyclient.api.cairh.itrus.req.CreateEnterpriseSealRequest;
import io.github.lucklike.luckyclient.api.cairh.itrus.req.CreateUserRequest;
import io.github.lucklike.luckyclient.api.cairh.itrus.req.DownloadContractRequest;
import io.github.lucklike.luckyclient.api.cairh.itrus.req.EnterpriseCreateRequest;
import io.github.lucklike.luckyclient.api.cairh.itrus.req.QueryEnterpriseRequest;
import io.github.lucklike.luckyclient.api.cairh.itrus.req.QuerySealRequest;
import io.github.lucklike.luckyclient.api.cairh.itrus.resp.AddSignerResponse;
import io.github.lucklike.luckyclient.api.cairh.itrus.resp.CreateAutographResponse;
import io.github.lucklike.luckyclient.api.cairh.itrus.resp.CreateContractResponse;
import io.github.lucklike.luckyclient.api.cairh.itrus.resp.CreateEnterpriseSealResponse;
import io.github.lucklike.luckyclient.api.cairh.itrus.resp.CreateUserResponse;
import io.github.lucklike.luckyclient.api.cairh.itrus.resp.DownloadContractResponse;
import io.github.lucklike.luckyclient.api.cairh.itrus.resp.EnterpriseCreateResponse;
import io.github.lucklike.luckyclient.api.cairh.itrus.resp.PageResponse;
import io.github.lucklike.luckyclient.api.cairh.itrus.resp.QueryEnterpriseResponse;
import io.github.lucklike.luckyclient.api.cairh.itrus.resp.Seal;
import io.github.lucklike.luckyclient.api.cairh.itrus.resp.Stamp;

@JsonBody
@PrintLog(allowBodyMaxLength = 1024)
@Retryable(retryCount = 5, multiplier = 2, exclude = {BizException.class})
@ItrusHttpClient
public interface ItrusApi {

    @Describe("天威合同创建用户")
    @Post("/user/create")
    CreateUserResponse createUser(CreateUserRequest request);

    @Describe("查询企业列表")
    @Post("/enterprise/pageQuery")
    QueryEnterpriseResponse queryEnterpriseList(QueryEnterpriseRequest request);

    @Describe("创建企业")
    @Post("/enterprise/create")
    EnterpriseCreateResponse createEnterprise(EnterpriseCreateRequest request);

    @Describe("个人签名列表")
    @Post("/stamp/stampList")
    PageResponse<Stamp> queryStampList(ContractStampListRequest request);

    @Describe("天威创建个人签名")
    @Post("/user/createAutograph")
    CreateAutographResponse createAutograph(CreateAutographRequest request);

    @Describe("企业印章列表")
    @Post("/seal/sealList")
    PageResponse<Seal> querySealList(QuerySealRequest request);

    @Describe("创建企业印章")
    @Post("/enterprise/createSeal")
    CreateEnterpriseSealResponse createEnterpriseSeal(CreateEnterpriseSealRequest request);

    @Describe("添加⼦企业")
    @Post("/enterprise/addSubCompany")
    void addSubCompany(AddSubCompanyRequest request);

    @Describe("天威合同创建合同⼦企业")
    @Post("/contract/createByFile")
    CreateContractResponse createFileContract(CreateContractRequest request);

    @Describe("天威合同上添加签收人信息")
    @Post("/contract/addSignerByFile")
    AddSignerResponse addSignerByFile(AddSignerRequest request);

    @Describe("天威合同签署")
    @Post("/contract/signByFile")
    void signFileContract(ContractSignRequest request);

    @Describe("天威合同下载签署后的合同")
    @Post("/contract/downloadContract")
    DownloadContractResponse downloadContract(DownloadContractRequest request);

}
