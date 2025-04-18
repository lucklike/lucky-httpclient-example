package io.github.lucklike.luckyclient.api.cairh.comp.verify;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.luckyframework.httpclient.proxy.annotations.JsonBody;
import com.luckyframework.httpclient.proxy.annotations.JsonParam;
import com.luckyframework.httpclient.proxy.annotations.Post;
import io.github.lucklike.luckyclient.api.cairh.annotations.CRHApi;
import io.github.lucklike.luckyclient.api.cairh.comp.verify.req.PoliceVerifyConfig;
import io.github.lucklike.luckyclient.api.cairh.comp.verify.req.PoliceVerifyConfigPage;
import io.github.lucklike.luckyclient.api.cairh.comp.verify.resp.SysWorkTimeResp;

import java.util.List;

@CRHApi(project = "/comp/policeVerifyConfig/")
public interface PoliceVerifyConfigApi {

    @Post("queryByPage")
    Page<PoliceVerifyConfig> queryByPage(@JsonBody PoliceVerifyConfigPage request);

    @Post("queryOne")
    PoliceVerifyConfig queryOne(@JsonParam String serial_id);

    @Post("insert")
    String insert(@JsonBody PoliceVerifyConfig request);

    @Post("update")
    String update(@JsonBody PoliceVerifyConfig request);

    @Post("delete")
    String delete(@JsonParam String serial_id);

    @Post("getAllTimeNameInfo")
    List<SysWorkTimeResp> getAllTimeNameInfo();

}
