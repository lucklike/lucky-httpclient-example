package io.github.lucklike.luckyclient.api.cairh.itrus.resp;

import io.github.lucklike.luckyclient.api.cairh.BizException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class ItrusBaseResponse<T> {

    private Integer status;
    private String message;
    private T data;

    /**
     * 获取响应数据或者抛出异常
     *
     * @param methodInfo 接口方法信息，一般传接口名
     * @return 响应数据
     */
    public T getDataOrThrowException(String methodInfo) {
        if (status != 1) {
            String errorMsg = String.format("【%s】接口响应转态码异常：status=%s, message=%s", methodInfo, status, message);
            log.error(errorMsg);
            throw new BizException("110541", errorMsg);
        }
        return data;
    }

}
