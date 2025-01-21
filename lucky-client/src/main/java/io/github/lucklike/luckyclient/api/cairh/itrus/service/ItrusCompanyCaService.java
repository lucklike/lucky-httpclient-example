package io.github.lucklike.luckyclient.api.cairh.itrus.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import io.github.lucklike.luckyclient.api.cairh.BizException;
import io.github.lucklike.luckyclient.api.cairh.itrus.ItrusCommonParam;
import io.github.lucklike.luckyclient.api.cairh.itrus.api.ItrusApi;
import io.github.lucklike.luckyclient.api.cairh.itrus.req.AddSignerRequest;
import io.github.lucklike.luckyclient.api.cairh.itrus.req.ContractSignRequest;
import io.github.lucklike.luckyclient.api.cairh.itrus.req.CreateContractRequest;
import io.github.lucklike.luckyclient.api.cairh.itrus.req.DownloadContractRequest;
import io.github.lucklike.luckyclient.api.cairh.itrus.resp.AddSignerResponse;
import io.github.lucklike.luckyclient.api.cairh.itrus.resp.CreateContractResponse;
import io.github.lucklike.luckyclient.api.cairh.itrus.resp.DownloadContractResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
public class ItrusCompanyCaService {

    @Resource
    private ItrusApi itrusApi;

    @Resource
    private ItrusCommonParam commonParam;

    public byte[] createCAPdf(CaRequest caRequest) {

        // 参数校验
        paramCheck(caRequest);

        // 获取签名位置信息
        SealLocalList sealLocalList = SealLocalList.forCaRequest(caRequest);

        // 创建用户组件
        UserComponent userComp = UserComponent.of(itrusApi, commonParam, caRequest);

        // 创建签章组件
        SealComponent sealComp = SealComponent.of(itrusApi, commonParam, caRequest, userComp);

        // 创建天威电子合同
        CreateContractResponse contractInfo = createContract(caRequest, userComp, sealLocalList);

        // 添加天威电子合同签署人信息
        AddSignerResponse signInfo = addSignerInfo(sealLocalList, userComp, contractInfo);

        // 上传文件合同后台签署
        signContract(signInfo, contractInfo.getContractId(), sealComp);

        // 调用天威电子合同下载合同接口，获取盖章的协议文件流
        return downloadContract(contractInfo.getContractId());
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
     * 创建合同
     *
     * @param caRequest CA请求对象
     * @param userComp  用户组件
     * @return 文件合同信息
     */
    private CreateContractResponse createContract(CaRequest caRequest, UserComponent userComp, SealLocalList sealLocalList) {
        CreateContractRequest apiRequest = new CreateContractRequest();
        apiRequest.setSignCount(String.valueOf(sealLocalList.getSize()));
        apiRequest.setName(caRequest.getAgreement_name());
        apiRequest.setDocName(caRequest.getAgreement_name());
        apiRequest.setBase64(Base64Utils.encodeToString(caRequest.getFile()));
        apiRequest.setCreator(userComp.getUserId());
        apiRequest.setEnterpriseId(userComp.getSubCompanyId());
        apiRequest.setWaterMarkOff("false");
        return itrusApi.createFileContract(apiRequest);
    }

    /**
     * 添加签署人信息
     *
     * @param sealLocalList 签名位置信息
     * @param userComp      用户组件
     * @param contractInfo  合同信息
     * @return 签署信息
     */
    private AddSignerResponse addSignerInfo(SealLocalList sealLocalList, UserComponent userComp, CreateContractResponse contractInfo) {

        String contractId = contractInfo.getContractId();
        String docId = contractInfo.getDocId();

        AddSignerRequest apiRequest = new AddSignerRequest();
        apiRequest.setContractId(contractId);
        // 签署人集合
        List<AddSignerRequest.Signer> signerList = new ArrayList<>();
        apiRequest.setSigners(signerList);

        int ctrlId = 1;

        //签名
        if (sealLocalList.hasSignLocal()) {
            for (Local signLocal : sealLocalList.getSignLocals()) {
                signerList.add(signLocal.toSigner(docId, "1", userComp, ctrlId++));
            }
        }

        //印章
        if (sealLocalList.hasSealLocal()) {
            for (Local sealLocal : sealLocalList.getSealLocals()) {
                signerList.add(sealLocal.toSigner(docId, "2", userComp, ctrlId++));
            }
        }

        return itrusApi.addSignerByFile(apiRequest);
    }

    private void signContract(AddSignerResponse signInfo, String contractId, SealComponent sealComp) {

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
                    controlValue.setStampId(sealComp.getSignId());
                } else {

                    // 子企业
                    if (ctrlId == 2) {
                        controlValue.setSealId(sealComp.getSubSealId());
                    } else {
                        controlValue.setSealId(sealComp.getMainSealId());
                    }

                }
                controlValue.setControlsId(ctrlId);
                signFile.setControlValues(Collections.singletonList(controlValue));
                signFileList.add(signFile);
                ctrlId++;
            }
            sign.setSignFiles(signFileList);
            signReq.setSigner(sign);

            itrusApi.signFileContract(signReq);
        }
    }

    /**
     * 下载合同
     *
     * @param contractId 合同ID
     * @return 合同文件
     */
    private byte[] downloadContract(String contractId) {
        DownloadContractRequest apiRequest = new DownloadContractRequest();
        apiRequest.setContractId(contractId);
        DownloadContractResponse response = itrusApi.downloadContract(apiRequest);
        return response.getData();
    }

}
