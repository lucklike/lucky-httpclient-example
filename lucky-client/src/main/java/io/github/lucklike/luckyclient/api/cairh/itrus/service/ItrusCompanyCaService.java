package io.github.lucklike.luckyclient.api.cairh.itrus.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.github.lucklike.luckyclient.api.cairh.BizException;
import io.github.lucklike.luckyclient.api.cairh.itrus.ItrusCommonParam;
import io.github.lucklike.luckyclient.api.cairh.itrus.api.ItrusApi;
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
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class ItrusCompanyCaService {

    @Resource
    private ItrusApi itrusApi;

    @Resource
    private ItrusCommonParam commonParam;

    public byte[] createCAPdf(CaRequest caRequest) throws Exception {

        // 参数校验
        paramCheck(caRequest);

        // 调用天威电子合同创建用户接口，获取userId
        String userId = this.getUserId(caRequest);

        // 获取企业ID
        String enterpriseId = getEnterpriseId(caRequest, userId);

        // 获取个人签名
        Long autographId = queryAutographId(caRequest, userId);

        // 获取企业签章ID
        Long sealId = querySealId(caRequest, enterpriseId, userId);

        // 创建天威电子合同
        CreateContractResponse contractInfo = createContract(caRequest, enterpriseId, userId);

        // 添加天威电子合同签署人信息
        AddSignerResponse signInfo = addSignerInfo(caRequest, userId, enterpriseId, contractInfo);

        // 上传文件合同后台签署
        signContract(signInfo, contractInfo.getContractId(), sealId, autographId);

        // 调用天威电子合同下载合同接口，获取盖章的协议文件流
        return downloadItruscontract(contractInfo.getContractId());
    }

    /**
     * 必填参数校验
     *
     * @param caRequest CA请求对象
     */
    private void paramCheck(CaRequest caRequest) {
        // 检验是否配置了主企业ID
        if (!StringUtils.hasText(commonParam.getCompanyUUID())) {
            throw new BizException("未配置主企业ID[companyUUID], 请检查配置[cpe.protocol-gateway.service.ComponentItrusHttp.companyUUID]");
        }

        if (!StringUtils.hasText(caRequest.getCsdc_ent_name())) {
            throw new BizException("缺少必填参数csdc_ent_name");
        }

        if (!StringUtils.hasText(caRequest.getCsdc_busi_id_no())) {
            throw new BizException("缺少必填参数csdc_busi_id_no");
        }

        if (!StringUtils.hasText(caRequest.getSeal_image_base64())) {
            throw new BizException("缺少必填参数seal_image_base64");
        }
    }

    /**
     * 获取合同用户ID
     *
     * @param caRequest CA请求对象
     * @return 合同用户ID
     */
    private String getUserId(CaRequest caRequest) {
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
     * 创建合同
     *
     * @param caRequest    CA请求对象
     * @param enterpriseId 企业ID
     * @param userId       经办人ID
     * @return 文件合同信息
     */
    private CreateContractResponse createContract(CaRequest caRequest, String enterpriseId, String userId) {
        CreateContractRequest apiRequest = new CreateContractRequest();
        int signCount = 0;
        if (StringUtils.hasText(caRequest.getSeal_pos())) {
            signCount += 1;
        }
        if (StringUtils.hasText(caRequest.getSign_pos())) {
            signCount += 1;
        }
        if (signCount == 0) {
            throw new BizException("未找到签名区域配置seal_pos和签名区域配置sign_pos");
        }
        apiRequest.setSignCount(String.valueOf(signCount));
        apiRequest.setName(caRequest.getAgreement_name());
        apiRequest.setDocName(caRequest.getAgreement_name());
        apiRequest.setBase64(Base64Utils.encodeToString(caRequest.getFile()));
        apiRequest.setCreator(userId);
        apiRequest.setEnterpriseId(enterpriseId);
        apiRequest.setWaterMarkOff("false");
        return itrusApi.createFileContract(apiRequest);
    }

    /**
     * 添加签署人信息
     *
     * @param caRequest    CA请求对象
     * @param userId       用户Id
     * @param enterpriseId 企业ID
     * @param contractInfo 合同信息
     * @return 签署信息
     */
    private AddSignerResponse addSignerInfo(CaRequest caRequest, String userId, String enterpriseId, CreateContractResponse contractInfo) {

        AddSignerRequest apiRequest = new AddSignerRequest();
        apiRequest.setContractId(contractInfo.getContractId());
        // 签署人集合
        List<AddSignerRequest.Signer> signerList = new ArrayList<>();
        apiRequest.setSigners(signerList);

        int ctrlId = 1;

        //印章
        String sealPos = caRequest.getSeal_pos();
        if (StringUtils.hasText(sealPos)) {
            AddSignerRequest.Signer sealSigner = new AddSignerRequest.Signer();
            sealSigner.setUserId(userId);
            sealSigner.setEnterpriseId(enterpriseId);
            sealSigner.setSignerType("2");
            sealSigner.setSequence("2");

            // 构建印章签署文件
            AddSignerRequest.SignFile sealSignerFile = new AddSignerRequest.SignFile();
            sealSignerFile.setDocId(contractInfo.getDocId());

            // 初始化控件
            List<AddSignerRequest.XySignControl> xySignControlList = new ArrayList<>();
            List<AddSignerRequest.KeywordSignControl> keywordSignControlList = new ArrayList<>();
            JSONArray sealSignPosJson = createPosJsonArray(sealPos, "解析签章信位置息失败: " + sealPos);
            for (int i = 0; i < sealSignPosJson.size(); i++) {
                addSignControl(xySignControlList, keywordSignControlList, ctrlId++, "signet", sealSignPosJson.getJSONObject(i));
            }

            if (!xySignControlList.isEmpty()) {
                sealSignerFile.setXySignControls(xySignControlList);
            }
            if (!keywordSignControlList.isEmpty()) {
                sealSignerFile.setKeywordSignControls(keywordSignControlList);
            }

            sealSigner.setSignFiles(Collections.singletonList(sealSignerFile));
            signerList.add(sealSigner);
        }

        //签名
        String signPos = caRequest.getSign_pos();
        if (StringUtils.hasText(signPos)) {
            AddSignerRequest.Signer signer = new AddSignerRequest.Signer();
            signer.setUserId(userId);
            signer.setSignerType("1");
            signer.setSequence("1");

            // 构建印章签署文件
            AddSignerRequest.SignFile sealSignerFile = new AddSignerRequest.SignFile();
            sealSignerFile.setDocId(contractInfo.getDocId());

            // 初始化控件
            List<AddSignerRequest.XySignControl> xySignControlList = new ArrayList<>();
            List<AddSignerRequest.KeywordSignControl> keywordSignControlList = new ArrayList<>();
            JSONArray signPosJson = createPosJsonArray(signPos, "解析签名信位置息失败: " + signPos);
            for (int i = 0; i < signPosJson.size(); i++) {
                addSignControl(xySignControlList, keywordSignControlList, ctrlId++, "autograph", signPosJson.getJSONObject(i));
            }

            if (!xySignControlList.isEmpty()) {
                sealSignerFile.setXySignControls(xySignControlList);
            }
            if (!keywordSignControlList.isEmpty()) {
                sealSignerFile.setKeywordSignControls(keywordSignControlList);
            }

            signer.setSignFiles(Collections.singletonList(sealSignerFile));
            signerList.add(signer);
        }
        return itrusApi.addSignerByFile(apiRequest);
    }

    private void signContract(AddSignerResponse signInfo, String contractId, Long sealId, Long autographId) {

        int ctrlId = 1;
        for (AddSignerResponse.Signer signer : signInfo.getSigners()) {
            ContractSignRequest signReq = new ContractSignRequest();

            Integer signerType = signer.getSignerType();
            signReq.setContractId(contractId);
            ContractSignRequest.Signer sign = new ContractSignRequest.Signer();
            sign.setSignerId(signer.getSignerId());

            List<ContractSignRequest.SignFile> signFileList = new ArrayList<>();
            for (AddSignerResponse.Document document : signer.getDocList()) {
                ContractSignRequest.SignFile signFile = new ContractSignRequest.SignFile();
                signFile.setDocId(document.getDocId());
                ContractSignRequest.ControlValue controlValue = new ContractSignRequest.ControlValue();
                if (signerType == 1) {
                    controlValue.setStampId(autographId);
                } else {
                    controlValue.setSealId(sealId);
                }
                controlValue.setControlsId(ctrlId++);
                signFile.setControlValues(Collections.singletonList(controlValue));
                signFileList.add(signFile);
            }
            sign.setSignFiles(signFileList);
            signReq.setSigner(sign);

            itrusApi.signFileContract(signReq);
        }
    }

    private byte[] downloadItruscontract(String contractId) throws Exception {
        DownloadContractRequest apiRequest = new DownloadContractRequest();
        apiRequest.setContractId(contractId);
        DownloadContractResponse response = itrusApi.downloadContract(apiRequest);
        return response.getData();
    }

    /**
     * 获取企业ID，先查询，如果存在直接返回，不存在时进行创建，第一次创建时需要把当前企业添加到主企业下
     *
     * @param caRequest CA请求实例
     * @param userId    用户ID
     * @return 企业ID
     */
    private String getEnterpriseId(CaRequest caRequest, String userId) {

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
        addSubCompany(commonParam.getCompanyUUID(), enterpriseId);
        return enterpriseId;
    }

    /**
     * 获取用户签名ID，先查询，如果存在直接返回，不存在时进行创建
     *
     * @param caRequest CA请求实例
     * @param userId    用户ID
     * @return 签名ID
     */
    private Long queryAutographId(CaRequest caRequest, String userId) {

        // 先走查询接口查询签名，能查到直接返回
        ContractStampListRequest queryReq = new ContractStampListRequest();
        queryReq.setUserId(userId);
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
        createReq.setUserId(userId);
        createReq.setAutographType("1");
        createReq.setAutographName(caRequest.getUser_name());
        CreateAutographResponse createResp = itrusApi.createAutograph(createReq);
        return createResp.getAutographId();
    }

    /**
     * 获取印章ID，先查询，如果存在直接返回，不存在时进行创建
     *
     * @param caRequest    CA请求实例
     * @param enterpriseId 企业ID
     * @param userId       用户ID
     * @return 印章ID
     */
    private Long querySealId(CaRequest caRequest, String enterpriseId, String userId) {

        // 先走查询接口查询印章，能查到直接返回
        QuerySealRequest queryReq = new QuerySealRequest();
        queryReq.setEnterpriseId(enterpriseId);
        PageResponse<Seal> queryResp = itrusApi.querySealList(queryReq);
        for (Seal seal : queryResp.getList()) {
            Long id = seal.getId();
            if (id != null) {
                return id;
            }
        }

        // 查不到时进行创建
        CreateEnterpriseSealRequest request = new CreateEnterpriseSealRequest();
        request.setUserId(userId);
        request.setEnterpriseId(enterpriseId);
        // 自定义方式创建
        request.setSealType(2);
        request.setSealName(caRequest.getCsdc_ent_name());
        request.setSealBase64(caRequest.getSeal_image_base64());
        CreateEnterpriseSealResponse response = itrusApi.createEnterpriseSeal(request);
        return response.getSealId();
    }

    /**
     * 将子企业添加到主企业下
     *
     * @param enterpriseId   主企业ID
     * @param subCompanyUuid 子企业ID
     */
    private void addSubCompany(String enterpriseId, String subCompanyUuid) {
        AddSubCompanyRequest request = new AddSubCompanyRequest();
        request.setEnterpriseId(enterpriseId);
        request.setSubCompanyUuid(subCompanyUuid);
        itrusApi.addSubCompany(request);
    }

    private JSONArray createPosJsonArray(String posJsonStr, String errorMsg) {
        try {
            JSONArray jsonArray = JSON.parseArray(posJsonStr);
            if (jsonArray == null || jsonArray.isEmpty()) {
                throw new BizException("-1", errorMsg);
            }
            return JSON.parseArray(posJsonStr);
        } catch (Exception e) {
            log.error(errorMsg, e);
            throw new BizException("-1", errorMsg);
        }
    }

    private void addSignControl(List<AddSignerRequest.XySignControl> xySignControlList,
                                List<AddSignerRequest.KeywordSignControl> keywordSignControlList,
                                int id,
                                String type,
                                JSONObject signPosJson) {
        // 签名类型
        String signType = signPosJson.getString("signtype");
        //关键字
        String kw = signPosJson.getString("kw");
        //x轴偏移量
        String xOffset = signPosJson.getString("xOffset");
        //y轴偏移量
        String yOffset = signPosJson.getString("yOffset");
        Float height = signPosJson.getFloat("height");
        Float width = signPosJson.getFloat("width");
        //第几页
        String index = signPosJson.getString("index");


        // 如果为1，则是位置定位，否则为关键字定位
        if (Objects.equals("1", signType)) {
            AddSignerRequest.XySignControl xySignControl = new AddSignerRequest.XySignControl();
            xySignControl.setId(id);
            xySignControl.setType(type);
            xySignControl.setPageNum(index);
            xySignControl.setX(String.valueOf((Float.parseFloat(xOffset) / 100f)));
            xySignControl.setY(String.valueOf((Float.parseFloat(yOffset) / 100f)));
            if (height != null) {
                xySignControl.setHeight(height);
            }
            if (width != null) {
                xySignControl.setWidth(width);
            }
            xySignControlList.add(xySignControl);
        } else {
            AddSignerRequest.KeywordSignControl keywordSignControl = new AddSignerRequest.KeywordSignControl();
            keywordSignControl.setId(id);
            keywordSignControl.setType(type);
            keywordSignControl.setPageNum(index);
            keywordSignControl.setOffsetX(xOffset);
            keywordSignControl.setOffsetY(yOffset);
            keywordSignControl.setKeyword(kw);
            if (height != null) {
                keywordSignControl.setHeight(height);
            }
            if (width != null) {
                keywordSignControl.setWidth(width);
            }
            keywordSignControlList.add(keywordSignControl);
        }
    }

}
