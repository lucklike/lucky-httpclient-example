package io.github.lucklike.luckyclient.api.inject;

import com.luckyframework.httpclient.generalapi.download.RangeDownloadApi;
import io.github.lucklike.httpclient.annotation.HttpReference;
import org.springframework.stereotype.Service;

@Service
public class FileService {

    @HttpReference
    private RangeDownloadApi rangeDownloadApi;

    public void downloadFile(String path, String saveDir) {
        rangeDownloadApi.download(path, saveDir);
    }
}
