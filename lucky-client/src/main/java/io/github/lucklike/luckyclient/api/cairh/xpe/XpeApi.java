package io.github.lucklike.luckyclient.api.cairh.xpe;

import com.luckyframework.common.StringUtils;
import com.luckyframework.httpclient.proxy.annotations.DownloadToLocal;
import com.luckyframework.httpclient.proxy.annotations.FormParam;
import com.luckyframework.httpclient.proxy.annotations.Post;
import com.luckyframework.httpclient.proxy.annotations.RespConvert;
import com.luckyframework.httpclient.proxy.annotations.SSL;
import com.luckyframework.httpclient.proxy.annotations.StaticForm;
import io.github.lucklike.httpclient.discovery.HttpClient;

import java.io.File;

@SSL(enable = "#{$url$.startsWith('https')}")
@HttpClient("${cairh.xpe.url:http://localhost:8080}")
public interface XpeApi {

    String MODEL_PATH = "/agreements/CRH-EA1022.json";
    String ARCH_PATH = "/arch/CRH-ARCH1002.json";
    String XPE_FILE_CONVERTER = "#{{fileData: $byteBody$, fileName: $resp$.getDownloadFilename()}}";

    @DownloadToLocal
    @Post(MODEL_PATH)
    @StaticForm("agreement_download_type=0")
    File downloadModelFile(@FormParam String agreement_no, @FormParam String agreement_version);

    @DownloadToLocal
    @Post(ARCH_PATH)
    @FormParam
    File downloadArchFile(String archfileinfo_id, String file_md5);

    @RespConvert(XPE_FILE_CONVERTER)
    @Post(MODEL_PATH)
    @StaticForm({"agreement_download_type=0", "#{uuid()}=#{str('{}_{}_{}', agreement_no, agreement_version, uuid)}"})
    XpeFile getXpeModelFile(@FormParam String agreement_no, @FormParam String agreement_version, String uuid);

    static String str(String temp, Object... args) {
        return "#string@" + StringUtils.format(temp, args);
    }

    @RespConvert(XPE_FILE_CONVERTER)
    @Post(ARCH_PATH)
    @FormParam
    XpeFile getXpeArchFile(String archfileinfo_id, String file_md5);

}
