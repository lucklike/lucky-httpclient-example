package io.github.lucklike.luckyclient.api.cairh.itrus.service;

import com.alibaba.fastjson.JSON;
import com.luckyframework.common.StringUtils;
import io.github.lucklike.luckyclient.api.cairh.BizException;
import io.github.lucklike.luckyclient.api.cairh.itrus.req.AddSignerRequest;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Data
public class SealLocalList {

    private AtomicInteger ctrlId = new AtomicInteger(1);

    /**
     * 签名位置
     */
    private List<Local> signLocals = new ArrayList<>();

    /**
     * 印章位置
     */
    private List<Local> sealLocals = new ArrayList<>();


    public static SealLocalList forCaRequest(CaRequest caRequest) {
        SealLocalList list = new SealLocalList();
        String sealPos = caRequest.getSeal_pos();
        String signPos = caRequest.getSign_pos();

        if (StringUtils.hasText(sealPos)) {
            list.setSealLocals(JSON.parseArray(sealPos, Local.class));
        }

        if (StringUtils.hasText(signPos)) {
            list.setSignLocals(JSON.parseArray(signPos, Local.class));
        }
        list.check();
        return list;
    }

    public int getSize() {
        return signLocals.size() + sealLocals.size();
    }

    public boolean hasSignLocal() {
        return !signLocals.isEmpty();
    }

    public boolean hasSealLocal() {
        return !sealLocals.isEmpty();
    }

    public void resetCtrlId() {
        ctrlId.set(0);
    }

    public int getCtrlIdAndIncrement() {
        return ctrlId.getAndIncrement();
    }

    public void check() {
        if (getSize() == 0) {
            throw new BizException("未找到签名区域配置seal_pos和签名区域配置sign_pos");
        }
    }

    public List<AddSignerRequest.Signer> getAllSigners(String docId, UserComponent user) {
        List<AddSignerRequest.Signer> signers = new ArrayList<>();
        if (hasSignLocal()) {
            signers.add(getPersonageSigner(docId, user));
        }
        if (hasSealLocal()) {
            signers.add(getCompanySigner(docId, user));
        }
        return signers;
    }

    public AddSignerRequest.Signer getCompanySigner(String docId, UserComponent user) {
        AddSignerRequest.Signer signer = new AddSignerRequest.Signer();
        signer.setSignerType("2");
        signer.setSequence("2");
        signer.setUserId(user.getUserId());
        signer.setEnterpriseId(user.getSubCompanyId());
        String ctrlType = "signet";

        List<AddSignerRequest.SignFile> signFileList = new ArrayList<>(sealLocals.size());
        AddSignerRequest.SignFile signFile = new AddSignerRequest.SignFile();
        signFile.setDocId(docId);

        // 初始化控件
        List<AddSignerRequest.XySignControl> xySignControlList = new ArrayList<>();
        List<AddSignerRequest.KeywordSignControl> keywordSignControlList = new ArrayList<>();
        for (Local local : sealLocals) {

            if (local.isKw()) {
                keywordSignControlList.add(local.toKwSignControl(ctrlType, getCtrlIdAndIncrement()));
            } else {
                xySignControlList.add(local.toXySignControl(ctrlType, getCtrlIdAndIncrement()));
            }
            if (!xySignControlList.isEmpty()) {
                signFile.setXySignControls(xySignControlList);
            }
            if (!keywordSignControlList.isEmpty()) {
                signFile.setKeywordSignControls(keywordSignControlList);
            }
        }

        signFileList.add(signFile);
        signer.setSignFiles(signFileList);
        return signer;
    }

    public AddSignerRequest.Signer getPersonageSigner(String docId, UserComponent user) {
        AddSignerRequest.Signer signer = new AddSignerRequest.Signer();
        signer.setSignerType("1");
        signer.setSequence("1");
        signer.setUserId(user.getUserId());
        String ctrlType = "autograph";

        List<AddSignerRequest.SignFile> signFileList = new ArrayList<>(signLocals.size());
        AddSignerRequest.SignFile signFile = new AddSignerRequest.SignFile();
        signFile.setDocId(docId);

        // 初始化控件
        List<AddSignerRequest.XySignControl> xySignControlList = new ArrayList<>();
        List<AddSignerRequest.KeywordSignControl> keywordSignControlList = new ArrayList<>();
        for (Local local : signLocals) {
            if (local.isKw()) {
                keywordSignControlList.add(local.toKwSignControl(ctrlType, getCtrlIdAndIncrement()));
            } else {
                xySignControlList.add(local.toXySignControl(ctrlType, getCtrlIdAndIncrement()));
            }
            if (!xySignControlList.isEmpty()) {
                signFile.setXySignControls(xySignControlList);
            }
            if (!keywordSignControlList.isEmpty()) {
                signFile.setKeywordSignControls(keywordSignControlList);
            }
            signFileList.add(signFile);
        }
        signer.setSignFiles(signFileList);
        return signer;
    }

}
