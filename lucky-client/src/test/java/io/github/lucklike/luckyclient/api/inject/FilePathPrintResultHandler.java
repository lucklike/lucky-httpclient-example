package io.github.lucklike.luckyclient.api.inject;

import com.luckyframework.httpclient.proxy.handle.ResultContext;
import com.luckyframework.httpclient.proxy.handle.ResultHandler;

import java.io.File;

public class FilePathPrintResultHandler implements ResultHandler<File> {

    @Override
    public void handleResult(ResultContext<File> resultContext) throws Throwable {
        File file = resultContext.getResult();
        System.out.println(file.getAbsolutePath());
    }
}
