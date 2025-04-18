package io.github.lucklike.luckyclient.api.cairh.comp.verify;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.github.lucklike.luckyclient.api.cairh.comp.verify.req.PoliceVerifyConfig;
import io.github.lucklike.luckyclient.api.cairh.comp.verify.req.PoliceVerifyConfigPage;
import io.github.lucklike.luckyclient.api.cairh.comp.verify.resp.SysWorkTimeResp;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PoliceVerifyConfigApiTest {

    @Resource
    private PoliceVerifyConfigApi policeVerifyConfigApi;

    @Test
    void queryByPage() {
        PoliceVerifyConfigPage policeVerifyConfigPage = new PoliceVerifyConfigPage();
        policeVerifyConfigPage.setSize(15);
        Page<PoliceVerifyConfig> page = policeVerifyConfigApi.queryByPage(policeVerifyConfigPage);
        System.out.println(page);
    }

    @Test
    void queryOne() {
        PoliceVerifyConfig verifyConfig = policeVerifyConfigApi.queryOne("20250415604330090000");
        System.out.println(verifyConfig);
    }

    @Test
    void insert() {
        PoliceVerifyConfig verifyConfig = new PoliceVerifyConfig();
        verifyConfig.setFactory_name("haiTongIdVerifyService");
        verifyConfig.setPriority_level("1");
        verifyConfig.setIs_high_definition("1");
        verifyConfig.setTime_kind("7");
        verifyConfig.setSubsys_id("14");
        String insertResult = policeVerifyConfigApi.insert(verifyConfig);
        System.out.println(insertResult);
    }

    @Test
    void update() {
        PoliceVerifyConfig verifyConfig = new PoliceVerifyConfig();
        verifyConfig.setSerial_id("20250418416390070000");
        verifyConfig.setFactory_name("csdcIdVerifyService");
        verifyConfig.setPriority_level("1");
        verifyConfig.setIs_high_definition("1");
        verifyConfig.setTime_kind("7");
        verifyConfig.setSubsys_id("14");
        String updateResult = policeVerifyConfigApi.update(verifyConfig);
        System.out.println(updateResult);

    }

    @Test
    void delete() {
        String deleteResult = policeVerifyConfigApi.delete("20250418416390070000");
        System.out.println(deleteResult);
    }

    @Test
    void getAllTimeNameInfo() {
        List<SysWorkTimeResp> allTimeNameInfo = policeVerifyConfigApi.getAllTimeNameInfo();
        System.out.println(allTimeNameInfo);
    }
}